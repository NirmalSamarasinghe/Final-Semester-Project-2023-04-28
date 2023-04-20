package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB_connection {
    private final static String URL = "jdbc:mysql://localhost:3306/londoncoffeeshop";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    private static DB_connection db_connection;
    private Connection connection;

    private DB_connection() throws SQLException {
        connection = DriverManager.getConnection(URL, props);
    }

    public static DB_connection getInstance() throws SQLException {
        if (db_connection == null) {
            return db_connection =new DB_connection();
        }else {
            return db_connection;
        }
    }

    public  Connection getConnection() {return connection;}
}
