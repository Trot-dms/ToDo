package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by trot on 26.02.17.
 */

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int catId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Todo> todoId;


    public Category(String name, List<Todo> todoId) {
        this.name = name;
        this.todoId = todoId;
    }

    public Category() {
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodoId() {
        return todoId;
    }

    public void setTodoId(List<Todo> todoId) {
        this.todoId = todoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (getCatId() != category.getCatId()) return false;
        if (getName() != null ? !getName().equals(category.getName()) : category.getName() != null) return false;
        return getTodoId() != null ? getTodoId().equals(category.getTodoId()) : category.getTodoId() == null;
    }

    @Override
    public int hashCode() {
        int result = getCatId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTodoId() != null ? getTodoId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catId=" + catId +
                ", name='" + name + '\'' +
                ", todoId=" + todoId +
                '}';
    }
}
