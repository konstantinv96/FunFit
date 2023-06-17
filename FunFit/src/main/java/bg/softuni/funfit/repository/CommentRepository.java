package bg.softuni.funfit.repository;

import bg.softuni.funfit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
