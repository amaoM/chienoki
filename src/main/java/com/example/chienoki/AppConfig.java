package com.example.chienoki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.chienoki.dao.RssDao;
import com.example.chienoki.service.ArticleService;
import com.example.chienoki.service.HostService;

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
