package com.acruent.admin.service;

import java.util.List;

import com.acruent.admin.entity.Category;

public interface CategoryService 
{
	public String createCategory(Category category);
	public Category getCategoryById(Integer id) throws Exception;
	public List<Category> getAllCatagories();
	public String updateCategoryById(Category category , Integer id) throws Exception;
	public String deleteCategoryById(Integer id) throws Exception;

}
