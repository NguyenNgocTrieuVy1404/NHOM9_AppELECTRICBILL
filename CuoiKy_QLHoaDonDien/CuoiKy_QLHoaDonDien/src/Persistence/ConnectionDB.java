package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB connectionDB;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/dbqlhoadontiendien";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789@Vy";

    public static ConnectionDB getInstance() throws SQLException, ClassNotFoundException {
        if (connectionDB == null) {
            connectionDB = new ConnectionDB();
        }
        return connectionDB;
    }
    
    private ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public Connection getConnection() {
        return connection;
    }
}