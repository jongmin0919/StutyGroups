package sample;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTest {

    @Test
    public void testConnection() throws Exception{
    Class.forName("org.mariadb.jdbc.Driver");

    String url = "jdbc:mariadb://localhost:3306/springdb";
    Connection con = DriverManager.getConnection(url, "springdbuser", "springdbuser");

    System.out.println(con);

    con.close();
    }
}
