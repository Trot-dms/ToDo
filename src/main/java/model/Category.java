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

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Todo> todo;


    public Category(String name, List<Todo> todo) {
        this.name = name;
        this.todo = todo;
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

    public List<Todo> getTodo() {
        return todo;
    }

    public void setTodo(List<Todo> todoId) {
        this.todo = todoId;
    }

}
