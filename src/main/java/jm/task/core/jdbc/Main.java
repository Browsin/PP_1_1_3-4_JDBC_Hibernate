package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
//        1. Создание таблицы User
        userService.createUsersTable();

//        2. Добавление userov
        userService.saveUser("Pavel", "Brosin", (byte) 25);
        userService.saveUser("Alexandr", "Kaiurov", (byte) 25);
        userService.saveUser("Michael", "Jackson", (byte) 50);
        userService.saveUser("Billie", "Jean", (byte) 80);

//        3. Лист пользователей
        for (User user: userService.getAllUsers()) {
            System.out.println(user);
        }

//        4. Очистка таблицы
        userService.cleanUsersTable();

//        5. Удаление таблицы
        userService.dropUsersTable();




    }
}
