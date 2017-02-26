package dao;

import model.Todo;

/**
 * Created by trot on 26.02.17.
 */
public interface TodoDAO {
    Todo findById(int id);
    void create(Todo todo);
    void update(Todo todo);
    void delete(Todo todo);
}
