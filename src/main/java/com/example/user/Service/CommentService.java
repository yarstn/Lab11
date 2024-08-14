package com.example.user.Service;

import com.example.user.ApiException.ApiException;
import com.example.user.Model.Comment;
import com.example.user.Model.Post;
import com.example.user.Model.User;
import com.example.user.Repository.CommentRepository;
import com.example.user.Repository.PostRepository;
import com.example.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }
    public void addComment(Comment comment){
        User user = userRepository.findUserById(comment.getUserId());
        Post post = postRepository.findByid(comment.getPostId());
        if (user == null || post == null){
            throw  new ApiException("user or post is null");
        }
        commentRepository.save(comment);

    }
    public void updateComment(Integer id,Comment comment){
        Comment comment1 = commentRepository.findCommentById(id);
        if (comment1 == null){
            throw  new ApiException("comment not found");
        }
        comment1.setContent(comment.getContent());
        commentRepository.save(comment1);
    }
    public void deleteComment(Integer id){
        Comment comment1 = commentRepository.findCommentById(id);
        if (comment1 == null){
            throw  new ApiException("comment not found");
        }
        commentRepository.delete(comment1);
    }
    public List<Comment> getCommentByUserId(int userId){
        User user = userRepository.findUserById(userId);
        List<Comment> comments = commentRepository.findCommentByUserId(user.getId());
        if (comments == null){
            throw  new ApiException("comment not found");
        }
        return comments;
    }
    public List<Comment> getCommentsByKeyword(String keyword) {
        List<Comment> comments = commentRepository.findByContentContaining(keyword);
        if (comments.isEmpty()) {
            throw new ApiException("No comments found containing the keyword");
        }
        return comments;
    }
public List<Comment> getCommentByPostId(int postId){
        Post post = postRepository.findByid(postId);
        List<Comment> comments = commentRepository.findByCommentByPostId(post.getId());
        if (comments.isEmpty()) {
            throw new ApiException("No comments found containing the post");
        }
        return comments;
}
}
