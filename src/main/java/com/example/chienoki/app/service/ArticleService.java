package com.example.chienoki.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.chienoki.domain.Article;
import com.example.chienoki.domain.ArticleRepository;

/**
 * @author amaomasashi
 *
 */
@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    /**
     * @param pageNumber
     * @return
     */
    public List<Article> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, 3, Sort.Direction.ASC, "id");
        return articleRepository.findAll(request).getContent();
    }

    /**
     * @param articleId
     */
    public void deleteArticle(Long articleId) {
        articleRepository.delete(articleId);
    }
}
