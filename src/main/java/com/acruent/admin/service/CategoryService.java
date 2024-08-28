package com.acruent.admin.service;

import java.util.List;

import com.acruent.admin.entity.Category;
import com.acruent.admin.exceptionhandle.CategoryNotFoundException;

public interface CategoryService 
{
	public String createCategory(Category category);
	public Category getCategoryById(Integer id) throws CategoryNotFoundException;
	public List<Category> getAllCatagories();
	public String updateCategoryById(Category category , Integer id) throws CategoryNotFoundException;
	public String deleteCategoryById(Integer id) throws CategoryNotFoundException;
    public  boolean categoryStatusChange(Integer categoryId, String status);

}
