package com.example.chienoki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.chienoki.domain.Article;
import com.example.chienoki.domain.ArticleRepository;

public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getPage(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, 3, Sort.Direction.ASC, "id");
		return articleRepository.findAll(request).getContent();
	}
}
