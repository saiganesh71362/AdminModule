package com.acruent.admin.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acruent.admin.appconstants.AdminModuleAppConstants;
import com.acruent.admin.entity.Category;
import com.acruent.admin.exceptionhandle.IdNotFoundException;
import com.acruent.admin.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private static final Logger logger = LogManager.getLogger(CategoryController.class);

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/newCategory")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		logger.info(AdminModuleAppConstants.REQUEST_SEND_NEW_CATEGORY, category);
		String createCategory = categoryService.createCategory(category);
		if (createCategory != null) {
			logger.info(AdminModuleAppConstants.CATEGORY_CREATED, createCategory);
			return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
		} else {
			logger.error(AdminModuleAppConstants.CATEGORY_CREATION_FAILD);
			return new ResponseEntity<>(AdminModuleAppConstants.CATEGORY_CREATION_FAILD,
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) throws IdNotFoundException {
		logger.info("Received request to get Category by ID: {}", id);

		Category categoryById = categoryService.getCategoryById(id);
		logger.info("Successfully retrieved Category with ID: {}", id);
		return new ResponseEntity<>(categoryById, HttpStatus.FOUND);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllCategories() {
		logger.info("Received request to get all categories");

		List<Category> allCategories = categoryService.getAllCatagories(); // external Dependency

		if (!allCategories.isEmpty()) {
			logger.info("Successfully retrieved {} categories", allCategories.size());
		} else {
			logger.warn("No categories found");
		}

		return new ResponseEntity<>(allCategories, HttpStatus.FOUND);

	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<String> updateCategoryById(@PathVariable Integer id, @RequestBody Category category)
			throws IdNotFoundException {
		logger.info("Received request to update Category with ID: {}", id);
		logger.debug("Category details: {}", category);

		String updateCategoryById = categoryService.updateCategoryById(category, id);

		logger.info("Successfully updated Category with ID: {}", id);
		return new ResponseEntity<>(updateCategoryById, HttpStatus.OK);

	}

	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Integer id) throws IdNotFoundException {
		logger.info("Received request to delete Category with ID: {}", id);

		String result = categoryService.deleteCategoryById(id);

		logger.info("Successfully deleted Category with ID: {}", id);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PatchMapping("/categoryStatusChange/{status}/{id}")
	public ResponseEntity<String> categoryStatusChange(@PathVariable String status, @PathVariable Integer id) {
		logger.info("Received request to change status of Category with ID: {} to status: {}", id, status);

		boolean categoryStatusChanged = categoryService.categoryStatusChange(id, status);
		String msg;

		if (categoryStatusChanged) {
			msg = "Successfully updated status for Category ID: " + id;
			logger.info(msg);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			msg = "Failed to update status for Category ID: " + id;
			logger.warn(msg);
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

		}
	}
}
