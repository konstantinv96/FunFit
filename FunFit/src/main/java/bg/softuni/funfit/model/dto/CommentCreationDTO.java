package bg.softuni.funfit.model.dto;

public class CommentCreationDTO {
    private String username;
    private Long workoutId;
    private String message;

    public CommentCreationDTO() {
    }

    public CommentCreationDTO(String username, Long workoutId, String message) {
        this.username = username;
        this.workoutId = workoutId;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
