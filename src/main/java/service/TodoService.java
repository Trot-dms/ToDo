package service;

import com.google.gson.Gson;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.TodoDAO;
import dao.TodoDAOImpl;
import dto.CategoryDTO;
import dto.Mapper;
import dto.TodoDTO;
import model.Category;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by trot on 28.02.17.
 */
public class TodoService {

    private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private static Logger LOGGER;
    private static TodoDAO todoDAO;
    private static CategoryDAO categoryDAO;

    public TodoService() {
        todoDAO = new TodoDAOImpl(factory);
        categoryDAO = new CategoryDAOImpl(factory);
    }

    public void setLogger(Logger LOGGER) {
        TodoService.LOGGER = LOGGER;
    }

    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> list = todoDAO.findAll().stream().map(Mapper::todoMapper).collect(Collectors.toList());
        LOGGER.info("Getting all todos..." + list.size() + " items");
        return list;
    }

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> list = categoryDAO.findAll().stream().map(Mapper::categoryMapper).collect(Collectors.toList());
        LOGGER.info("Getting all categories..." + list.size() + " items");
        return list;
    }

    public CategoryDTO getCategoryByName(Request req, Response res) {
        setJsonResponse(res);
        return checkCategoryFromQuery(categoryDAO.findByName(req.params("name")));
    }

    public CategoryDTO getCategoryById(Request req, Response res) {
        setJsonResponse(res);
        int id = Integer.parseInt(req.params("id"));
        return checkCategoryFromQuery(categoryDAO.findById(id));
    }

    public String createTodo(Request req, Response response) {
        String body = req.body();
        String title = req.attribute("taskName");
        String description = req.attribute("taskDesc");
        String category = req.params("category");
//
//        Todo todo = new Todo();
//        todo.setTitle(title);
//        todo.setDescription(description);
//        todo.setCategory(categoryDAO.findByName(category).get());
//
//        todoDAO.create(todo);
//        return req.body();

        return body;
    }

    public void setupDB() {
        if (categoryDAO.findAll().isEmpty() || categoryDAO.findAll() == null) {
            SetupDB.data(factory);
        }
    }

    private void setJsonResponse(Response response) {
        response.type("application/json");
    }

    private CategoryDTO checkCategoryFromQuery(Optional<Category> queryResult) {
        if (queryResult.isPresent()) {
            LOGGER.info("Found category " + queryResult.get().getName());
            return queryResult.map(Mapper::categoryMapper).get();
        } else {
            LOGGER.info("No category found, returning empty.");
            return new CategoryDTO();
        }
    }
}
