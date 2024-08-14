package com.example.user.Repository;

import com.example.user.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findByid(Integer id);

//    List<Post> findByPostId(Integer postId);

//get post by title
    List<Post> findByTitle(String title);
//    get all post before date by date
@Query("select p from Post p where p.publishDate < ?1")
List<Post> findAllByPublishDateBefore(Date publishDate);
//    get all post by CategoryId
    List<Post> findByCategoryId(int categoryId);



}
