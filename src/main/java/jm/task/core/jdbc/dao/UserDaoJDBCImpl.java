package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS User("
            + "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
            + "name VARCHAR(45) NOT NULL , lastName VARCHAR(45) NOT NULL , "
            + "age TINYINT NOT NULL)";
    private static final String INSERT_INTO = "INSERT INTO User("
            + "name, lastName, age) VALUES (?, ?, ?)";
    private static final String DELETE_FROM = "DELETE FROM User WHERE id = ";
    private static final String SELECT_FROM = "SELECT * FROM User";
    private static final String CLEAN_TABLE_QUERY = "DELETE FROM User";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS User";
    private Connection connection;


    public UserDaoJDBCImpl() {
        final Util util = new Util();
        if (connection == null) connection = util.connect();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            throw new IllegalStateException("Не получилось создать таблицу: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_QUERY);
        } catch (SQLException e) {
            throw new IllegalStateException("Не получилось удалить таблицу: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка добаления пользователя: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_FROM + id);
        } catch (SQLException e) {
            throw new IllegalStateException("Не получилось удалить пользователя: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка создания листа пользователей: " + e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CLEAN_TABLE_QUERY);
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка очиститки таблицы: " + e.getMessage());
        }
    }
}
