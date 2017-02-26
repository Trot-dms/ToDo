package dao;

import model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by trot on 26.02.17.
 */
public class CategoryDAOImpl implements CategoryDAO {

    private final SessionFactory sessionFactory;
    private GenericCrud genericCrud;

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        genericCrud = new GenericCrud(sessionFactory);
    }

    @Override
    public Optional<Category> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Category.class, id));
        }
    }

    @Override
    public List<Category> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(Category.class).list();
        }
    }
}
