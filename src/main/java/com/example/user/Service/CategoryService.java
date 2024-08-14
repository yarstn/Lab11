package com.example.user.Service;

import com.example.user.Model.Category;
import com.example.user.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void updateCategory(Integer id,Category category) {
        Category category1 = categoryRepository.findByid(id);
        if (category1 == null) {
            throw new RuntimeException("Category not found");
        }
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }
    public void deleteCategory(Integer id) {
        Category category1 = categoryRepository.findByid(id);

        if (category1 == null) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.delete(category1);

    }
}
