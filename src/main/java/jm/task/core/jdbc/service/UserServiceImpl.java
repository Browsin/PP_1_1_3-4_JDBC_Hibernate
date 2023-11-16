package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
        logger.info("Таблица создана");
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
        logger.info("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        logger.info("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
        logger.info("удалил пользователя с id = " + id);
    }

    public List<User> getAllUsers() {
        List<User> userList = userDaoHibernate.getAllUsers();
        logger.info("Все пользователи в листе");
        return userList;
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
        logger.info("Очистил таблицу");
    }
}
