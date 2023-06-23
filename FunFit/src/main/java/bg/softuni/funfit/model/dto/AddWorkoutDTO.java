package bg.softuni.funfit.model.dto;

import bg.softuni.funfit.model.enums.Level;

import javax.validation.constraints.*;

public class AddWorkoutDTO {

    @NotBlank
    @Size(min=3, max=50)
    private String name;

    @NotBlank
    @Size(min=3, max=50)
    private String mainGoal;

    @Positive
    @NotNull
    private double sessionTime;

    @NotNull
    private Level level;

    @Positive
    @NotNull
    private int duration;

    @Positive
    @NotNull
    @Min(1)
    @Max(7)
    private int daysPerWeek;

    @NotBlank
    private String description;

    @NotBlank
    private String imageURL;

    private String workoutAuthor;

    public AddWorkoutDTO() {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getWorkoutAuthor() {
        return workoutAuthor;
    }

    public void setWorkoutAuthor(String workoutAuthor) {
        this.workoutAuthor = workoutAuthor;
    }
}
