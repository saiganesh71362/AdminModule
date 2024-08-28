package com.acruent.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acruent.admin.appconstants.AdminModuleAppConstants;
import com.acruent.admin.controller.CategoryController;
import com.acruent.admin.entity.Category;
import com.acruent.admin.serviceimpl.CategoryServiceImpl;

@SpringBootTest
class CategoryControllerTest {
	@Mock
	CategoryServiceImpl categoryServiceImpl;

	@InjectMocks
	CategoryController categoryController;

	@Test
	@Order(1)
	void test_createCategory_success() {
	    // Arrange
	    Integer id = 1;
	    Category category = new Category("CHILD", "N", "Admin", "Admin");
	    category.setCategoryId(id);

	    // Mock the service method to return the expected response
	    String expectedMessage = "Record Created Success Fully :" + id;
	    when(categoryServiceImpl.createCategory(category)).thenReturn(expectedMessage);

	    // Act
	    ResponseEntity<String> createCategoryResponse = categoryController.createCategory(category);

	    // Assert
	    assertEquals(HttpStatus.CREATED, createCategoryResponse.getStatusCode());
	    assertEquals(expectedMessage, createCategoryResponse.getBody());

	    // Verify that the service method was called exactly once
	    verify(categoryServiceImpl, times(1)).createCategory(category);
	}
	@Test
	@Order(2)
	void test_createCategory_failure() {
	    // Arrange
	    Category category = new Category("CHILD", "N", "Admin", "Admin");

	    // Mock the service method to return null, indicating failure
	    when(categoryServiceImpl.createCategory(category)).thenReturn(null);

	    // Act
	    ResponseEntity<String> createCategoryResponse = categoryController.createCategory(category);

	    // Assert
	    assertEquals(HttpStatus.BAD_REQUEST, createCategoryResponse.getStatusCode());
	    assertEquals(AdminModuleAppConstants.CATEGORY_CREATION_FAILD, createCategoryResponse.getBody());

	    // Verify that the service method was called exactly once
	    verify(categoryServiceImpl, times(1)).createCategory(category);
	}
	@Test
	@Order(3)
	void test_getCategoryById() throws Exception {
		Integer id = 3;
		Category category3 = new Category("CHAILD", "N", "Admin", "Admin");
		category3.setCategoryId(id);

		when(categoryServiceImpl.getCategoryById(id)).thenReturn(category3);
		ResponseEntity<Category> categoryById = categoryController.getCategoryById(id);
		assertEquals(HttpStatus.FOUND, categoryById.getStatusCode());
		assertEquals(3, categoryById.getBody().getCategoryId());
	}

	@Test
	@Order(4)
	void test_getAllCategories() {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		Category category1 = new Category("FOOD", "Y", "Admin", "Admin");
		Category category2 = new Category("MEDICAL", "N", "Admin", "Admin");
		Category category3 = new Category("CHAILD", "N", "Admin", "Admin");

		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);

		when(categoryServiceImpl.getAllCatagories()).thenReturn(categoryList); // Mock

		ResponseEntity<List<Category>> allCategories = categoryController.getAllCategories();

		assertEquals(HttpStatus.FOUND, allCategories.getStatusCode());
		assertEquals(3, allCategories.getBody().size());
	}

	@Test
	@Order(5)
	void test_updateCategoryById() throws Exception {
		Integer id = 3;

		Category category3 = new Category("MEDICAID", "N", "Admin", "Admin");

		when(categoryServiceImpl.updateCategoryById(category3, id)).thenReturn("MEDICAID");

		ResponseEntity<String> updateCategoryById = categoryController.updateCategoryById(id, category3);

		assertEquals(HttpStatus.OK, updateCategoryById.getStatusCode());
		assertEquals("MEDICAID", updateCategoryById.getBody());
		categoryServiceImpl.updateCategoryById(category3, id);
	}

	@Test
	@Order(6)
	void test_deleteCategoryById() throws Exception {
		Integer id = 3;

//		Category category3 = new Category("MEDICAID", "N", "Admin", "Admin");

		when(categoryServiceImpl.deleteCategoryById(id)).thenReturn("MEDICAID");

		ResponseEntity<String> deleteCategoryById = categoryController.deleteCategoryById(id);

		assertEquals(HttpStatus.OK, deleteCategoryById.getStatusCode());
		assertEquals("MEDICAID", deleteCategoryById.getBody());
		categoryServiceImpl.deleteCategoryById(id);
	}

	@Test
	@Order(7)
	void test_categoryStatusChange_success() {
	    // Arrange
	    Integer id = 3;
	    String status = "N";

	    // Mock the service method to return true, indicating success
	    when(categoryServiceImpl.categoryStatusChange(id, status)).thenReturn(true);

	    // Act
	    ResponseEntity<String> response = categoryController.categoryStatusChange(status, id);

	    // Assert
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Successfully updated status for Category ID: " + id, response.getBody());

	    // Verify that the service method was called with the correct parameters
	    verify(categoryServiceImpl, times(1)).categoryStatusChange(id, status);
	}
	
	@Test
	@Order(8)
	void test_categoryStatusChange_failure() {
	    // Arrange
	    Integer id = 3;
	    String status = "N";

	    // Mock the service method to return false, indicating failure
	    when(categoryServiceImpl.categoryStatusChange(id, status)).thenReturn(false);

	    // Act
	    ResponseEntity<String> response = categoryController.categoryStatusChange(status, id);

	    // Assert
	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals("Failed to update status for Category ID: " + id, response.getBody());

	    // Verify that the service method was called with the correct parameters
	    verify(categoryServiceImpl, times(1)).categoryStatusChange(id, status);
	}
}
