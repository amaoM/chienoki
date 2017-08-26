package com.example.chienoki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.chienoki.app.service.ArticleService;
import com.example.chienoki.app.service.HostService;
import com.example.chienoki.batch.dao.RssDao;

@Configuration
public class AppConfig {

	@Bean
	public RssDao articleDao() {
		return new RssDao();
	}
	
	@Bean
	public ArticleService articleService() {
		return new ArticleService();
	}
	
	@Bean
	public HostService hostService() {
		return new HostService();
	}
}
