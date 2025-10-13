package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
public class DatabaseConnection {
    //private static final String URL = "jdbc:postgresql://localhost:5432/caminhofacil";
    Connection conn = DriverManager.getConnection(
    "jdbc:postgresql://db:5432/caminhofacil",
    "postgres",
    "postgres"
);
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
 */

 
public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("SPRING_DATASOURCE_URL");
        String user = System.getenv("SPRING_DATASOURCE_USERNAME");
        String pass = System.getenv("SPRING_DATASOURCE_PASSWORD");

        return DriverManager.getConnection(url, user, pass);
    }
}