package com.acruent.admin.service;

import java.util.List;

import com.acruent.admin.entity.Category;
import com.acruent.admin.exceptionhandle.CategoryNotFoundException;
import com.acruent.admin.exceptionhandle.IdNotFoundException;

public interface CategoryService 
{
	public String createCategory(Category category);
	public Category getCategoryById(Integer id) throws CategoryNotFoundException;
	public List<Category> getAllCatagories();
	public String updateCategoryById(Category category , Integer id) throws IdNotFoundException;
	public String deleteCategoryById(Integer id) throws IdNotFoundException;
    public  boolean categoryStatusChange(Integer categoryId, String status);

}
