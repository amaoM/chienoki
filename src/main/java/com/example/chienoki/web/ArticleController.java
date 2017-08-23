package com.example.chienoki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chienoki.domain.Article;
import com.example.chienoki.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public @ResponseBody Iterable<Article> getArticles(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		return articleService.getPage(pageNumber);
	}
}
