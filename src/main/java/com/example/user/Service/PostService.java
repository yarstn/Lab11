package com.example.user.Service;

import com.example.user.ApiException.ApiException;
import com.example.user.Model.Category;
import com.example.user.Model.Post;
import com.example.user.Model.User;
import com.example.user.Repository.CategoryRepository;
import com.example.user.Repository.PostRepository;
import com.example.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public void addPost( Post post) {
        Category category1 = categoryRepository.findByid(post.getId());
        User user1 = userRepository.findUserById(post.getUserId());
        if (category1 == null && user1 == null) {
            throw new ApiException("Category or user not found");
        }
        postRepository.save(post);
    }

//
//    public void addPost(Post post) {
//
//        if (categoryRepository.existsById(post.getCategoryId()) && userRepository.existsById(post.getId())) {
//            post.setUserId(post.getUserId());
//
//        }
//    }
    public void updatePost(Integer id,Post post) {
        Post post1 = postRepository.findByid(id);
        if (post1 == null) {
          throw  new ApiException("Post Not Found");
        }
        post1.setCategoryId(post.getCategoryId());
        post1.setContent(post.getContent());
        post1.setTitle(post.getTitle());
        post1.setPublishDate(post.getPublishDate());
        postRepository.save(post1);

    }
    public void deletePost(Integer id) {
        Post post = postRepository.findByid(id);
        if (post == null) {
            throw  new ApiException("Post Not Found");
        }
        postRepository.delete(post);
    }
    public List<Post> getPostByTitle(String title) {
        List<Post> post = postRepository.findByTitle(title);
        if (post == null) {
            throw  new ApiException("Post Not Found");
        }
        return post;
    }
    public List<Post> getPostByPublishDate(Date publishDate) {
        List<Post> dates = postRepository.findAllByPublishDateBefore(publishDate);
        if (dates == null) {
            throw  new ApiException("Post Not Found");
        }
        return dates;
    }
    public List<Post> getPostsByCategoryId(int categoryId) {
        Category category1 = categoryRepository.findByid(categoryId);
        List<Post> posts = postRepository.findByCategoryId(category1.getId());
        if (posts.isEmpty()) {
            throw new ApiException("No posts found for this category");
        }
        return posts;
    }


}
