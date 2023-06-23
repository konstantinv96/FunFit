package bg.softuni.funfit.model;

import bg.softuni.funfit.model.enums.Level;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mainGoal;

    @Column(nullable = false)
    private double sessionTime;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private int daysPerWeek;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String description;

    private String pictureURL;
    private String videoURL;

    @ManyToMany
    private Set<Category> categories;

    @ManyToOne
    private User author;

    @OneToMany(targetEntity = Comment.class, mappedBy = "workout", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public Workout(){
        this.comments = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public Workout(String name, String mainGoal, double sessionTime, Level level, int duration, int daysPerWeek, String description, String pictureURL) {
        this.name = name;
        this.mainGoal = mainGoal;
        this.sessionTime = sessionTime;
        this.level = level;
        this.duration = duration;
        this.daysPerWeek = daysPerWeek;
        this.description = description;
        this.pictureURL = pictureURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String mainGoal) {
        this.mainGoal = mainGoal;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public double getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(double sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
