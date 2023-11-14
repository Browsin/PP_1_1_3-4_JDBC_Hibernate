package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

//    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public void createUsersTable() {
//        userDaoJDBC.createUsersTable();
        logger.info("Таблица создана");
    }

    public void dropUsersTable() {
//        userDaoJDBC.dropUsersTable();
        logger.info("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJDBC.saveUser(name, lastName, age);
        logger.info("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
//        userDaoJDBC.removeUserById(id);
        logger.info("удалил пользователя с id = " + id);
    }

    public List<User> getAllUsers() {
        logger.info("Все пользователи в листе");
        return null; //userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
//        userDaoJDBC.cleanUsersTable();
        logger.info("Очистил таблицу");
    }
}
