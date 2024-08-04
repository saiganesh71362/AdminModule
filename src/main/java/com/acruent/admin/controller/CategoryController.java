package com.acruent.admin.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acruent.admin.entity.Category;
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
    public ResponseEntity<String> createCategory(@RequestBody Category category)
    {
        logger.info("Request received to create a new category: {}", category);
        String createCategory = categoryService.createCategory(category);
        if (createCategory != null)
        {
            logger.info("Category created successfully: {}", createCategory);
            return new ResponseEntity<String>(createCategory, HttpStatus.CREATED);
        } 
        else
        {
            logger.error("Failed to create category.");
            return new ResponseEntity<String>("Category creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
