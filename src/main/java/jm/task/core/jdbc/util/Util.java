package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/DBase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my179sql"; //"my179sql";
    private static SessionFactory factory;

    public SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/DBase?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Browsin98");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                factory = configuration.buildSessionFactory(serviceRegistry);
            } catch (RuntimeException e) {
                throw new IllegalStateException("Ошибка установки соединения: " + e.getMessage());
            }
        }
        return factory;
    }

    public void closeSessionFactory() {
        if (factory != null) {
            factory.close();
            System.out.println("Фабрика сессий закрыта");
        }
    }

    public Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установлено");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка установки соединения: " + e.getMessage());
        }
    }
}
