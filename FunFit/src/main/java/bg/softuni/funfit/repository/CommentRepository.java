package bg.softuni.funfit.repository;

import bg.softuni.funfit.model.Comment;
import bg.softuni.funfit.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<List<Comment>> findAllByWorkout(Workout workout);
}
