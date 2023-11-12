package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    //todo: исчез README
    //todo: WARN! именовать ветки нужно по их назначению ..заставляет эксперементировать и вызывает боль (в команде не допустимо)

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Pavel", "Brosin", (byte) 25);
        userService.saveUser("Alexandr", "Kaiurov", (byte) 25);
        userService.saveUser("Michael", "Jackson", (byte) 50);
        userService.saveUser("Billie", "Jean", (byte) 80);
        //todo: нет реализации (не страшно ..у всех почему-то нет) - уделения User по Id
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}