package com.acruent.admin.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acruent.admin.entity.Category;
import com.acruent.admin.repository.CategoryRepository;
import com.acruent.admin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository)
	{
		this.categoryRepository = categoryRepository;
	}

	@Override
	public String createCategory(Category category) {

		Integer categoryId = category.getCategoryId();
		if (categoryId == null) {
			 Category saveCategory = categoryRepository.save(category);
			if (saveCategory != null && saveCategory.getCategoryId() != null) {
				return "Record Created Success Fully :" +saveCategory.getCategoryId();
			} else {
				return "Record Creation Faild"+saveCategory.getCategoryId();
			}

		} else {
			return "Record Already Created  :" + categoryId;
		}

	}

	@Override
	public Category getCategoryById(Integer id) throws Exception
	{
		Optional<Category> findById = categoryRepository.findById(id);
		if(findById.isPresent())
		{
			return findById.get();

		}
		 throw new Exception("Their is No Id :"+findById.get());
	}

	@Override
	public List<Category> getAllCatagories() 
	{
		List<Category> findAll = categoryRepository.findAll();
		return findAll;
	}

	@Override
	public String updateCategoryById(Category category, Integer id) throws Exception
	{
		 Category existingCategory = categoryRepository.findById(id).orElse(null);
		if(existingCategory!=null)
		{
			existingCategory.setCategoryName(category.getCategoryName());
			existingCategory.setActiveSwitch(category.getActiveSwitch());
			existingCategory.setCreatedDate(category.getCreatedDate());
			existingCategory.setUpdatedDate(category.getUpdatedDate());
			existingCategory.setCreatedBy(category.getCreatedBy());
			existingCategory.setUpdatedBy(category.getUpdatedBy());
			
			  categoryRepository.save(existingCategory);
			  return "Updated Recored Success Fully :"+existingCategory.getCategoryId();
		}
		throw new Exception("Their Is No Record :"+existingCategory.getCategoryId());
	}

	@Override
	public String deleteCategoryById(Integer id) throws Exception
	{
		Optional<Category> findById = categoryRepository.findById(id);
		if(findById.isPresent())
		{
			categoryRepository.deleteById(id);
			return "Record Deleted Success Fully :"+findById.get();
		}
		throw new Exception("Their Is No Record Id :"+findById.get());
	}
	
	

}
