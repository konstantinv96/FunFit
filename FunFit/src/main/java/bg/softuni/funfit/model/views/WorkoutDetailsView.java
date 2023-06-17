package bg.softuni.funfit.model.views;

public class WorkoutDetailsView {
    private long id;
    private String name;
    private String mainGoal;
    private double sessionTime;
    private String level;
    private int duration;
    private int daysPerWeek;
    private String description;
    private String pictureURL;
    private String videoURL;
    private String authorName;

    public WorkoutDetailsView() {
    }

    public WorkoutDetailsView(long id, String name, String mainGoal, double sessionTime,
                              String level, int duration, int daysPerWeek, String description
                            , String pictureURL, String videoURL, String authorName) {
        this.id = id;
        this.name = name;
        this.mainGoal = mainGoal;
        this.sessionTime = sessionTime;
        this.level = level;
        this.duration = duration;
        this.daysPerWeek = daysPerWeek;
        this.description = description;
        this.pictureURL = pictureURL;
        this.videoURL = videoURL;
        this.authorName = authorName;
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

    public double getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(double sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
