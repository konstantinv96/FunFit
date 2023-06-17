package bg.softuni.funfit.web.rest;

import bg.softuni.funfit.model.dto.CommentCreationDTO;
import bg.softuni.funfit.model.dto.CommentMessageDTO;
import bg.softuni.funfit.model.views.CommentDisplayView;
import bg.softuni.funfit.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    private CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{workoutId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getComments(@PathVariable("workoutId") Long workoutId){
            return ResponseEntity.ok(commentService.getAllCommentsForWorkout(workoutId));
    }

    @PostMapping(value = "/{workoutId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("workoutId") Long workoutId,
                                                           @AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody CommentMessageDTO commentDTO){

        CommentCreationDTO commentCreationDTO = new CommentCreationDTO(
                userDetails.getUsername(),
                workoutId,
                commentDTO.getMessage()
        );

        CommentDisplayView comment = commentService.createComment(commentCreationDTO);

        return ResponseEntity.created(URI.create(String.format("/api/%d/comments/%d",workoutId,comment.getId())))
                .body(comment);
    }

}
