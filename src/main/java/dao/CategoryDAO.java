package dao;

import model.Category;

import java.util.List;
import java.util.Optional;

/**
 * Created by trot on 26.02.17.
 */
public interface CategoryDAO {

    Optional<Category> findById(int id);

    List<Category> findAll();

    Optional<Category> findByName(String name);
}
