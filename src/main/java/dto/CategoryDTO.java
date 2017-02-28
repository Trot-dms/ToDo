package dto;

/**
 * Created by trot on 28.02.17.
 */
public class CategoryDTO {

    private int id;
    private String name;

    public CategoryDTO() {
        name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
