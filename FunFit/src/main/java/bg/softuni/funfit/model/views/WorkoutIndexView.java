package bg.softuni.funfit.model.views;

import bg.softuni.funfit.model.enums.Level;

public class WorkoutIndexView {
    private long id;
    private String name;
    private Level level;
    private String mainGoal;
    private int duration;
    private int daysPerWeek;

    private String pictureURL;

    public WorkoutIndexView() {
    }

    public WorkoutIndexView(long id, String name, Level level, String mainGoal, int duration, int daysPerWeek, String pictureURL) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.mainGoal = mainGoal;
        this.duration = duration;
        this.daysPerWeek = daysPerWeek;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String mainGoal) {
        this.mainGoal = mainGoal;
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
