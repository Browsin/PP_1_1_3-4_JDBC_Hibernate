package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Browsin98";

    public static Connection connect() throws SQLException{
        Connection connection = null;

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        if(!connection.isClosed()) {
            System.out.println("соединение установлено");
        } else {
            System.out.println("ошибка установки соединения");
        }
        return connection;
    }

}
