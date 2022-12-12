import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*
            Java'da methodlar return type sahibi olsa da olmasa da method olarak adlandirilir.
            SQL'de ise data return ediyorsa function denir. Return yapmiyorsa "procedure" olarak adlandirilir
         */


        /*
                                !!!!!!!!!!
            https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
            data tiplerinin sql'den jdbc java icin donusmus halini gosteren site
        */



        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","1234");
        Statement st = con.createStatement();

        // CallableStatement ile function cagirmayi parametrelendirecegiz.

        // 1.Adim : Function kodunu yaz
        String sql1 = "Create or Replace Function toplamaF(x Numeric ,y NUMERIC )\n" +
                "returns NUMERIC \n" +
                "Language plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        // 2.Adim : Function'i calistir.
        st.execute(sql1);

        // 3.Adim : Function'i cagir.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?,?)}"); // ilk ? data tipini verir

        // 4.Adim : Return icin registerOurParameter() methodunu, parametreler icin set() methodlarini uygula.
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        // 5.Adim : execute() methodu ile CallableStatement'i calistir.
        cst1.execute();

        // 6.Adim : Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));


        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        // 1.Adim : Function kodunu yaz
        String sql2 = "Create or Replace Function konininHacmiF(r Numeric ,h NUMERIC )\n" +
                "returns NUMERIC \n" +
                "Language plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        // 2.Adim : Function'i calistir.
        st.execute(sql2);

        // 3.Adim : Function'i cagir.
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmiF(?,?)}");

        // 4.Adim : Return icin registerOurParameter() methodunu, parametreler icin set() methodlarini uygula.
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        // 5.Adim : execute() methodu ile CallableStatement'i calistir.
        cst2.execute();

        // 6.Adim : Sonucu cagirmak icin return data type tipine gore
        System.out.println(String.format("%.2f", cst2.getBigDecimal(1))); // 6.28 virgulden sonra 2 basamagini getirecek sekilde formatladik

        // getBigDecimal ==> SQL deki NUMERIC data tipini JAVA/JDBC de bigDecimal olarak alıyoruz onu return ediyor.


        /*
                                !!!!!!!!!!
            https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
            data tiplerinin sql'den jdbc java icin donusmus halini gosteren site
        */


































    }


}
