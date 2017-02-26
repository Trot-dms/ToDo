import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.GenericCrud;
import enums.Crud;
import model.Category;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by trot on 26.02.17.
 */
public class CategoryDAOImplTest {

    private final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private CategoryDAO categoryDAO;
    private GenericCrud genericCrud;

    @Before
    public void init() {
        categoryDAO = new CategoryDAOImpl(factory);
        genericCrud = new GenericCrud(factory);
    }

    @Test
    public void finaAll() {
        Category category1 = new Category("Pilne", new ArrayList<>());
        genericCrud.execute(category1, Crud.CREATE);
        Category category2 = new Category("Dom", new ArrayList<>());
        genericCrud.execute(category2, Crud.CREATE);

        List<Category> list = categoryDAO.findAll();
        assertNotNull(list);
        assertEquals("Pilne", list.get(0).getName());
        assertEquals("Dom", list.get(1).getName());

        genericCrud.execute(category1, Crud.DELETE);
        genericCrud.execute(category2, Crud.DELETE);
    }

    @Test
    public void findAllDB() {
        List<Category> list = categoryDAO.findAll();
        assertNotNull(list);
    }
}
