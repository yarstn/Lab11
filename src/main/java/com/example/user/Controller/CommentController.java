package com.example.user.Controller;

import com.example.user.Model.Comment;
import com.example.user.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/get")
    public ResponseEntity GetComment() {
        return ResponseEntity.status(200).body(commentService.getAllComment());
    }
    @PostMapping("/add")
    public ResponseEntity AddComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body("Comment Added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity UpdateComment(@PathVariable Integer id,@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(201).body("Comment Updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(201).body("Comment Deleted successfully");
    }
    @GetMapping("/get/{id}")
    public ResponseEntity GetCommentById(@PathVariable int id) {
        List<Comment> comments = commentService.getCommentByUserId(id);
        if (comments.isEmpty()) {
            return ResponseEntity.status(404).body("Comment Not Found");
        }
        return ResponseEntity.status(200).body(comments);
    }
    @GetMapping("/comments/search/{keyword}")
    public ResponseEntity GetCommentsByKeyword(@PathVariable String keyword) {
        List<Comment> comments = commentService.getCommentsByKeyword(keyword);
        if (comments.isEmpty()) {
            return ResponseEntity.status(404).body("Comment Not Found");
        }
        return ResponseEntity.status(200).body(comments);
    }
    @GetMapping("/comments/{postId}")
    public ResponseEntity GetCommentsByPostId(@PathVariable int postId) {
        List<Comment> comments = commentService.getCommentByPostId(postId);
        if (comments.isEmpty()) {
            return ResponseEntity.status(404).body("Comment Not Found");
        }
        return ResponseEntity.status(200).body(comments);
    }

}
