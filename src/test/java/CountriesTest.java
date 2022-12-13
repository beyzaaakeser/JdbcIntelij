import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {

    /*
        Given
          User connects to the database

        When
          User sends the query to get the region ids from "countries" table
          Kullanıcı, "ülkeler" tablosundan bölge kimliklerini almak için sorguyu gönderir

        Then
          Assert that the number of region ids greater than 1 is 17.
          bolge numarasi 1den buyuk olan 17 tane ulke olmasi gerekiyo

        And
          User closes the connection
       */


    // test methodlari void olmak zorunda ve test methodlarinin oldugu yerde main method olmaz
    @Test
    public void test01() throws SQLException {
        //User connects to the database
        JdbcUtils.connectToDataBase("localhost","techproed","postgres","Mk.2243250");
        Statement statement = JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql1 = "SELECT region_id FROM countries";

        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<Integer> ids = new ArrayList<>();
        List<Integer> idsGreaterThan1 = new ArrayList<>();


        while (resultSet1.next()){
            ids.add(resultSet1.getInt(1));

        }
        System.out.println(ids);

        for(int w : ids){
            if (w>1){
                idsGreaterThan1.add(w);
            }
        }
        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);
        System.out.println(idsGreaterThan1.size());

        // Assert that the number of region ids greater than 1 is 17.
        Assert.assertEquals(17,idsGreaterThan1.size()); // 1. kisma beklenilen data (expected) data girilir, 2. kisma da buradan alinan data (actual) data girilir.

        // burada assertEquals yerine assertTrue da kullanilabiliyor.

        // User closes the connection
        JdbcUtils.closeConnectionAndStatement();

    }// test01

}
