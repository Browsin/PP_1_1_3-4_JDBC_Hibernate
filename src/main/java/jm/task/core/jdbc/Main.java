package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {//todo: sintax, codeStyle
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Pavel", "Brosin", (byte) 25);
        userService.saveUser("Alexandr", "Kaiurov", (byte) 25);
        userService.saveUser("Michael", "Jackson", (byte) 50);
        userService.saveUser("Billie", "Jean", (byte) 80);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
