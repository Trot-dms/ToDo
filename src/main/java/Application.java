import dao.TodoDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TodoService;

import static spark.Spark.*;

/**
 * Created by trot on 26.02.17.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoDAOImpl.class);
    private static final JsonTransformer jsonTransformer = new JsonTransformer();
    private static final TodoService service = new TodoService();

    public static void main(String[] args) {

        // Configure Spark
        exception(Exception.class, (e, req, res) -> LOGGER.error(e.getMessage()));
        port(4567);
        staticFiles.location("/static");
        staticFiles.expireTime(600L);

        // set service Logger
        service.setLogger(LOGGER);

        // Setup database
        service.setupDB();

        get("/", (req, res) -> {
            return "index.html";
        });
        get("/categories", (req, res) -> {
            res.type("application/json");
            return service.getAllCategories();
        }, jsonTransformer);

        get("/todos", (req, res) -> {
            res.type("application/json");
            return service.getAllTodos();
        }, jsonTransformer);

        get("/cat/name/:name", service::getCategoryByName, jsonTransformer);
        get("/cat/:id", service::getCategoryById, jsonTransformer);

        post("/todo", service::createTodo);

//        get("/task/:id", (req, res) -> todoDAO.findById(Integer.parseInt(req.params("id"))).get(), jsonTransformer);
    }

}
