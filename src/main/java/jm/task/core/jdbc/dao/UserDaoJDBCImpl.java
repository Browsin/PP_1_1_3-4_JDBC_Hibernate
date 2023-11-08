package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {




    public void createUsersTable() {
        String createTable = "create table User(" +
                "id bigint primary key not null auto_increment," +
                " name varchar(45) not null, lastName varchar(45) not null," +
                " age tinyint not null)";
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {

            statement.execute(createTable);
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            System.out.println("не получилось создать таблицу");
        }

    }

    public void dropUsersTable() {
        String dropTable = "drop table User";

        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {

            statement.execute(dropTable);
            System.out.println("Таблица удалена");

        } catch (SQLException e) {
            System.out.println("не получилось удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "insert into User(name, lastName, age) values(?, ?, ?)";

        try (Connection connection = Util.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println("ошибка добаления пользователя");
        }
    }

    public void removeUserById(long id) {
        String delete = "delete from User where id = " + id;

        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {

            statement.execute(delete);
            System.out.println("удалил пользователя с id = " + id);

        } catch (SQLException e) {
            System.out.println("не получилось удалить пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from User");

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

            System.out.println("все пользователи в листе");

        } catch (SQLException e) {
            System.out.println("не получилось создать лист пользователей");
        }
        return userList;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {

            statement.execute("delete from User");
            System.out.println("очистил таблицу");

        } catch (SQLException e) {
            System.out.println("не получилось очистить таблицу");
        }

    }
}
