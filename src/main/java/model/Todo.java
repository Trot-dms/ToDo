package model;

import javax.persistence.*;

/**
 * Created by trot on 26.02.17.
 */
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "comp")
    private boolean compleated;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "catId")
    private Category category;

    public Todo(int id, boolean compleated, String title, String description, Category category) {
        this.id = id;
        this.compleated = compleated;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleated() {
        return compleated;
    }

    public void setCompleated(boolean compleated) {
        this.compleated = compleated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriprion) {
        this.description = descriprion;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
