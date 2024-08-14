package com.example.user.Repository;

import com.example.user.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentById(Integer id);
//    get all comment for one post by UserId
    List<Comment> findCommentByUserId(int id);
//    get all comment by keyword
    List<Comment> findByContentContaining(String keyword);
    //    get all comment for one post by PostId
    @Query("select c from Comment c where c.postId=?1")
    List<Comment> findByCommentByPostId(int postId);


}
