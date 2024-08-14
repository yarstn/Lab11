package com.example.user.Controller;

import com.example.user.Model.Post;
import com.example.user.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/get")
    public ResponseEntity getPost(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        postService.addPost(post);
        return ResponseEntity.status(201).body("post added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(201).body("post updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(201).body("post deleted successfully");
    }
@GetMapping("/find/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
}
@GetMapping("/date/{date}")
    public ResponseEntity getPostByDate(@PathVariable Date date){
        return ResponseEntity.status(200).body(postService.getPostByPublishDate(date));
}
@GetMapping("/posts/category/{categoryId}")
    public ResponseEntity getPostByCategory(@PathVariable int categoryId){
        List<Post> postList = postService.getPostsByCategoryId(categoryId);
        if(postList.isEmpty()){
            return ResponseEntity.status(404).body("No posts found");
        }
        return ResponseEntity.status(200).body(postList);
}
}
