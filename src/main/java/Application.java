import dao.CategoryDAOImpl;
import dao.GenericCrud;
import dao.TodoDAOImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Created by trot on 26.02.17.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoDAOImpl.class);
    private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private static final JsonTransformer jsonTransformer = new JsonTransformer();
    private static TodoDAOImpl todoDAO;
    private static CategoryDAOImpl categoryDAO;
    private static GenericCrud genericCrud;

    public static void main(String[] args) {

        // Configure Spark
        exception(Exception.class, (e, req, res) -> LOGGER.error(e.getMessage()));
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        // Instantiate DAO
        todoDAO = new TodoDAOImpl(factory);
        categoryDAO = new CategoryDAOImpl(factory);
        genericCrud = new GenericCrud(factory);

        // Setup database
        setupDB();

        get("/categories", (req, res) -> categoryDAO.findAll(), jsonTransformer);
        get("/cat/:id", (req, res) -> categoryDAO.findById(Integer.parseInt(req.params("id"))).get(), jsonTransformer);

        get("/todos", (req, res) -> todoDAO.findAll(), jsonTransformer);
        get("/task/:id", (req, res) -> todoDAO.findById(Integer.parseInt(req.params("id"))).get(), jsonTransformer);

    }

    private static void setupDB() {
        if (categoryDAO.findAll().isEmpty() || categoryDAO.findAll() == null) {
            SetupDB.data(factory);
        }
    }
}
