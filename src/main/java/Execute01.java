import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Adim : Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        // 2.Adim : Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","1234");

        // 3.Adim : Statement olustur.
        Statement st = con.createStatement();

        // 4.Adim : Query calistir.
        System.out.println("Connection Success");

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        System.out.println("sql1 = "+ sql1); // false return eder cunku bir data cagirmiyoruz.

        /*
        execute() methodu DDL(Data Defination Language==> create, drop, alter table) ve DQL(select) icin kullanilabbilir.
        1) Eger execute() methodu DDL icin kullanilirsa false return eder.
        2) Eger execute() methodu DQL icin kullanilirsa data alirsak ==> true    alamazsak==> false verir.
           yani resultset alindiginda true verecek aksi halde false verir.
         */

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "ALTER TABLE workers ADD worker_adress VARCHAR(80)";
        st.execute(sql2);

        // 3.Ornek : workers tableini silin

        String sql3 = "Drop table workers";
        st.execute(sql3);



        // 5.Adim : Baglanti ve statement'i kapat.
        con.close();
        st.close();

    }
}
