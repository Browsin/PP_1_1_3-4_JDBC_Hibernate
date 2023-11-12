package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/DBase"; // не понимаю, что тут не так
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my179sql"; //"my179sql";

    public Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установлено");
            return connection;
        } catch (SQLException e) {
            System.out.println("Ошибка установки соединения");
        }
        return null;
    }
}
