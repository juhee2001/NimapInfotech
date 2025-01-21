package com.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.app.model.Category;

public interface CategoryService {

	public Page<Category> getAllCategories(Pageable pageable);

	public Category createCategory(Category cat);

	public Category getCategoryById(int id);

	public Category updateCategory(int id, Category cat);

	public void deleteCategory(int id);

	
}
