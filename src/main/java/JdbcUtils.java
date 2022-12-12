import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {


        private static Connection connection;
        private static Statement statement;

        public static Connection connectToDataBase()  { // ilk iki adim her kullandigimizda exception atmasin diye method yaptik
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            try {
                connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","1234");
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



        public static Statement createStatement(){ // 3. adim her seferinde exception atmasin diye method olusturduk
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return statement;

        }



}
