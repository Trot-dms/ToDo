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

    @Column(name = "desc")
    private String descriprion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catId")
    private Category category;

    public Todo(int id, boolean compleated, String title, String descriprion, Category category) {
        this.id = id;
        this.compleated = compleated;
        this.title = title;
        this.descriprion = descriprion;
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

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (getId() != todo.getId()) return false;
        if (isCompleated() != todo.isCompleated()) return false;
        if (getTitle() != null ? !getTitle().equals(todo.getTitle()) : todo.getTitle() != null) return false;
        if (getDescriprion() != null ? !getDescriprion().equals(todo.getDescriprion()) : todo.getDescriprion() != null)
            return false;
        return getCategory() != null ? getCategory().equals(todo.getCategory()) : todo.getCategory() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (isCompleated() ? 1 : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescriprion() != null ? getDescriprion().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", compleated=" + compleated +
                ", title='" + title + '\'' +
                ", descriprion='" + descriprion + '\'' +
                ", category=" + category +
                '}';
    }
}
