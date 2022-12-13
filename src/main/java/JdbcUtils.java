import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {



        private static Connection connection;
        private static Statement statement;


        // 2.Adim : Database'e baglan
        public static Connection connectToDataBase(String hostName,String dbName,String username,String password)  { // ilk iki adim her kullandigimizda exception atmasin diye method yaptik
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            try {
                connection= DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,username,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            if (connection!=null){
                System.out.println("Connection success");
            }else{
                System.out.println("Connection fail");
            }

            return connection;

        }


        // 3.Adim : Statement olustur.
        public static Statement createStatement(){ // 3. adim her seferinde exception atmasin diye method olusturduk
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return statement;

        }


        // 4.Adim : Query calistir.
        public static boolean execute(String sql){
            boolean isExecute;
            try {
                isExecute = statement.execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

           return isExecute;

        }


        // 5.Adim : Baglanti ve statement'i kapat.
        public static void closeConnectionAndStatement(){
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection.isClosed()&& statement.isClosed()){
                    System.out.println("Connection and Statement closed");
                }else{
                    System.out.println("Connection and Statement NOT closed");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        // Table olusturan method
        // "CREATE TABLE tableName(sutunAdi dataTipi,sutunAdi dataTipi...)"
        public static void createTable(String tableName, String... columnName_dateType ){

            StringBuilder columnName_dateValue = new StringBuilder();
            for(String w : columnName_dateType){
                columnName_dateValue.append(w).append(",");
            }

            columnName_dateValue.deleteCharAt(columnName_dateValue.length()-1);// ya da lastindexof(",") de denebilir.

            try {
                statement.execute("CREATE TABLE "+tableName+"("+columnName_dateValue+")");
                System.out.println("Table "+tableName+" successfully created");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }



}
