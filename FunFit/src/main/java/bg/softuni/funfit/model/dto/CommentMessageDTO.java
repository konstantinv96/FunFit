package bg.softuni.funfit.model.dto;

public class CommentMessageDTO {
    private String message;

    public CommentMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
