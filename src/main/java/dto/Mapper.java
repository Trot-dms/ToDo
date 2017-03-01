package dto;

import model.Category;
import model.Todo;

/**
 * Created by trot on 28.02.17.
 */
public class Mapper {
    public static CategoryDTO categoryMapper(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getCatId());
        dto.setName(category.getName());
        return dto;
    }

    public static TodoDTO todoMapper(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleated(todo.isCompleated());
        dto.setCategory(todo.getCategory().getName());
        return dto;
    }
}
