package com.example.chienoki.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.chienoki.domain.Category;

/**
 * @author amaomasashi
 *
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{
}
