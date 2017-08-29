package com.example.chienoki.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.chienoki.domain.Article;

/**
 * @author amaomasashi
 *
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>{
	
	Long countByLink(String link);
}
