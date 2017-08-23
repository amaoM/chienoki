package com.example.chienoki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chienoki.domain.Category;
import com.example.chienoki.domain.CategoryRepository;

@Controller
@RequestMapping(path="/category")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
