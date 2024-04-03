package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public Util() {}
    private static Connection connection;
    private static final String userName = "root";
    private static final String password = "llluxuryman";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/mydb";

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(connectionUrl, userName, password);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                System.out.println("Соединение не удалось.");
            }
        }
        return connection;
    }
}
