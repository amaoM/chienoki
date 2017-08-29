package com.example.chienoki.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chienoki.app.service.CategoryService;
import com.example.chienoki.domain.Category;

/**
 * @author amaomasashi
 *
 */
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody Iterable<Category> getCategories(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		return categoryService.getPage(pageNumber);
	}
	
	/**
	 * @param name
	 */
	@RequestMapping(value = "categories/save", method = RequestMethod.POST)
	public void saveCategory(@RequestParam(name = "name") String name) {
		categoryService.saveCategory(name);
	}
	
	/**
	 * @param categoryId
	 */
	@RequestMapping(value = "/categories/delete", method = RequestMethod.GET)
	public void deleteHost(@RequestParam(name = "id") Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
}
