package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS User("
            + "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
            + "name VARCHAR(45) NOT NULL , lastName VARCHAR(45) NOT NULL , "
            + "age TINYINT NOT NULL)";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS User";
    private final SessionFactory factory;
    private Transaction transaction;


    public UserDaoHibernateImpl() {
        final Util util = new Util();
        factory = util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE_QUERY)
                    .executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("Ошибка создания таблицы: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE_QUERY)
                    .executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("Ошибка удаления таблицы: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("Ошибка добаления пользователя: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User removeuser = session.get(User.class, id);
            if (removeuser != null) {
                session.delete(removeuser);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("Ошибка удаления пользователя: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.getCurrentSession()){
            transaction = session.beginTransaction();
            List<User> userList = session.createQuery("FROM User")
                    .list();
            transaction.commit();
            return userList;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("Ошибка создания листа пользователей: " + e.getMessage());
        }
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE User")
                    .executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("Ошибка очистки таблицы: " + e.getMessage());
        }
    }
}
