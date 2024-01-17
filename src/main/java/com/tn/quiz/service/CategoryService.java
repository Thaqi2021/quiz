package com.tn.quiz.service;

import com.tn.quiz.model.exam.Category;

import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
    public void deleteCategory(Long categoryId);
}
