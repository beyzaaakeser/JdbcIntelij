import java.sql.Connection;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {

        // 1.Adim : Driver'a kaydol
        // 2.Adim : Database'e baglan
        Connection connection = JdbcUtils.connectToDataBase("localhost","techproed","postgres","1234");

        // 3.Adim : Statement olustur.
        Statement statement = JdbcUtils.createStatement();

        // 4.Adim : Query calistir.
        //JdbcUtils.execute("Create table students (name VARCHAR(20), id INT,address VARCHAR(80))");

        // tablo olusturma methodunu kullan
        JdbcUtils.createTable("abcxy","name Varchar(10)", "id int ","address varchar(80)");

        // 5.Adim : Baglanti ve statement'i kapat.
        JdbcUtils.closeConnectionAndStatement();



    }



}
