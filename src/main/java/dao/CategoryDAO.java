package dao;

import model.Category;

import java.util.List;

/**
 * Created by trot on 26.02.17.
 */
public interface CategoryDAO {
    Category findById(int id);
    List<Category> findAll();
}
