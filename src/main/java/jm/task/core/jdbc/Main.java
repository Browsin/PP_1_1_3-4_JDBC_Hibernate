package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Pavel", "Brosin", (byte) 25);
        userService.saveUser("Alexandr", "Kaiurov", (byte) 25);
        userService.saveUser("Michael", "Jackson", (byte) 50);
        userService.saveUser("Billie", "Jean", (byte) 80);
        userService.removeUserById(3);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}