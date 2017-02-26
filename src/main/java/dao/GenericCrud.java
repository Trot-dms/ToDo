package dao;

import enums.Crud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by trot on 26.02.17.
 */
public class GenericCrud {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoDAOImpl.class);
    private final SessionFactory sessionFactory;

    public GenericCrud(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void execute(Object obj, Crud crud) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (crud.equals(Crud.CREATE)) {
                session.persist(obj);
                LOGGER.info("Created -> " + obj.toString());
            } else if (crud.equals(Crud.UPDATE)) {
                session.update(obj);
                LOGGER.info("Updated -> " + obj.toString());
            } else {
                session.delete(obj);
                LOGGER.info("Deleted -> " + obj.toString());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && !transaction.getRollbackOnly()) {
                transaction.rollback();
                LOGGER.warn("Transaction rollback -> Todo");
            }
            LOGGER.error("Exception thrown -> " + e.getMessage());
        }
    }
}
