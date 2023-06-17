package bg.softuni.funfit.service;

import bg.softuni.funfit.model.Comment;
import bg.softuni.funfit.model.User;
import bg.softuni.funfit.model.dto.CommentCreationDTO;
import bg.softuni.funfit.model.views.CommentDisplayView;
import bg.softuni.funfit.repository.CommentRepository;
import bg.softuni.funfit.repository.UserRepository;
import bg.softuni.funfit.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private WorkoutRepository workoutRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public CommentService(WorkoutRepository workoutRepository, UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDisplayView createComment(CommentCreationDTO commentDTO){
        User author = userRepository.findByUsername(commentDTO.getUsername()).get();

        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setWorkout(workoutRepository.getById(commentDTO.getWorkoutId()));
        comment.setAuthor(author);
        comment.setApproved(true);
        comment.setText(commentDTO.getMessage());

        commentRepository.save(comment);

        return new CommentDisplayView(comment.getId(),author.getFullName(),comment.getText());
    }
}
