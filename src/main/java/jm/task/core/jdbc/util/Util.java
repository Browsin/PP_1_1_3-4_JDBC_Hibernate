package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";//todo: комментарии - излишни
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my179sql";

    public Connection connect() {//todo: ошибка не пробрасывается, от static избавляемся - не ломаем ООП-парадигму
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        if(!connection.isClosed()) {
            System.out.println("соединение установлено");
        } else {
            System.out.println("ошибка установки соединения");
        }
        return connection;
    }
}
