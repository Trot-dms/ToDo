package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Created by trot on 26.02.17.
 */
public class SetupDB {

    private static void data(Connection connection) throws SQLException {

        InputStream inputStream = SetupDB.class.getClassLoader().getResourceAsStream("db.sql");
        String sqlContent = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));

        connection.createStatement().executeUpdate(sqlContent);
    }

    public static void data(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.doWork(SetupDB::data);
        }
    }
}
