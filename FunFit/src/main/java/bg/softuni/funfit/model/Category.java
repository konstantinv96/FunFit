package bg.softuni.funfit.model;

import bg.softuni.funfit.model.enums.WorkoutCategory;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private WorkoutCategory name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WorkoutCategory getName() {
        return name;
    }

    public void setName(WorkoutCategory name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
