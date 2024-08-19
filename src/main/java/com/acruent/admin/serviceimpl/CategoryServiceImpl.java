package com.acruent.admin.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acruent.admin.entity.Category;
import com.acruent.admin.exceptionhandle.CategoryNotFoundException;
import com.acruent.admin.exceptionhandle.IdNotFoundException;
import com.acruent.admin.repository.CategoryRepository;
import com.acruent.admin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public String createCategory(Category category) {

		Integer categoryId = category.getCategoryId();
		if (categoryId == null) {
			Category saveCategory = categoryRepository.save(category);
			if (saveCategory.getCategoryId() != null) {
				return "Record Created Success Fully :" + saveCategory.getCategoryId();
			} else {
				return "Record Creation Faild" + saveCategory.getCategoryId();
			}

		} else {
			return "Record Already Created  :" + categoryId;
		}

	}

	@Override
	public Category getCategoryById(Integer id) throws CategoryNotFoundException {
		Optional<Category> findById = categoryRepository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}

		throw new IdNotFoundException("There is no category with ID: " + id);
	}

	@Override
	public List<Category> getAllCatagories() {
		return categoryRepository.findAll();
	}

	@Override
	public String updateCategoryById(Category category, Integer id) throws IdNotFoundException {
		Category existingCategory = categoryRepository.findById(id).orElse(null);
		if (existingCategory != null) {
			existingCategory.setCategoryName(category.getCategoryName());
			existingCategory.setActiveSwitch(category.getActiveSwitch());
			existingCategory.setCreatedBy(category.getCreatedBy());
			existingCategory.setUpdatedBy(category.getUpdatedBy());

			categoryRepository.save(existingCategory);
			return "Updated Record Success Fully :" + existingCategory.getCategoryId();
		}
		throw new IdNotFoundException("Their Is No Record :" + category.getCategoryId());
	}

	@Override
	public String deleteCategoryById(Integer id) throws IdNotFoundException {
		Optional<Category> findById = categoryRepository.findById(id);

		if (findById.isPresent()) {
			categoryRepository.deleteById(id);
			return "Record Deleted Successfully: " + findById.get();
		}

		// Do not try to access findById.get() here, it is not present
		throw new IdNotFoundException("There is no record with ID: " + id);
	}

	@Override
	public boolean categoryStatusChange(Integer categoryId, String status) {
		Optional<Category> findById = categoryRepository.findById(categoryId);
		if (findById.isPresent()) {
			Category category = findById.get();
			category.setActiveSwitch(status != null ? status : "defaultValue");
			categoryRepository.save(category);
			return true;
		}
		return false;
	}

}
