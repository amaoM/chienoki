package com.example.chienoki.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.chienoki.domain.Category;
import com.example.chienoki.domain.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public Iterable<Category> getPage(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, 3, Sort.Direction.ASC, "id");
		return categoryRepository.findAll(request).getContent();
	}
	
	public void saveCategory(String name) {
		Category category = new Category();
		category.setName(name);
		categoryRepository.save(category);
	}
	
	public void saveCategory(Long id, String name) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		categoryRepository.save(category);
	}
	
	public void deleteCategory(Long categoryId) {
		categoryRepository.delete(categoryId);
	}
}
