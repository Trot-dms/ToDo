import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static spark.Spark.*;

/**
 * Created by trot on 26.02.17.
 */
public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (request, response) -> "Hello World");

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.close();
        sessionFactory.close();
    }
}
