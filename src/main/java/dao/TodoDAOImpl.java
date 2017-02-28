package dao;

import enums.Crud;
import model.Todo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by trot on 26.02.17.
 */
public class TodoDAOImpl implements TodoDAO {

    private final SessionFactory sessionFactory;
    private GenericCrud genericCrud;

    public TodoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        genericCrud = new GenericCrud(sessionFactory);
    }

    @Override
    public Optional<Todo> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Todo.class, id));
        }
    }

    @Override
    public void create(Todo todo) {
        genericCrud.execute(todo, Crud.CREATE);
    }

    @Override
    public void update(Todo todo) {
        genericCrud.execute(todo, Crud.UPDATE);
    }

    @Override
    public void delete(Todo todo) {
        genericCrud.execute(todo, Crud.DELETE);
    }

    @Override
    public List<Todo> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Todo");
            List list = query.getResultList();
            return list;
        }
    }
}
