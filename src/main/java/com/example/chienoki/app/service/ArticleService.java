package com.example.chienoki.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.chienoki.domain.Article;
import com.example.chienoki.domain.ArticleRepository;

@Component
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getPage(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, 3, Sort.Direction.ASC, "id");
		return articleRepository.findAll(request).getContent();
	}
	
	public void deleteArticle(Long articleId) {
		articleRepository.delete(articleId);
	}
}
