package dao;

import model.Todo;

import java.util.List;
import java.util.Optional;

/**
 * Created by trot on 26.02.17.
 */
public interface TodoDAO {

    Optional<Todo> findById(int id);

    void create(Todo todo);

    void update(Todo todo);

    void delete(Todo todo);

    List<Todo> findAll();
}
