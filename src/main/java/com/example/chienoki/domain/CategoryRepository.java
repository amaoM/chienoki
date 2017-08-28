package com.example.chienoki.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.chienoki.domain.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{
}
