package com.acruent.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acruent.admin.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
