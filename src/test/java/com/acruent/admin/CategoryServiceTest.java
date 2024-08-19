package com.acruent.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.acruent.admin.entity.Category;
import com.acruent.admin.repository.CategoryRepository;
import com.acruent.admin.serviceimpl.CategoryServiceImpl;

@SpringBootTest(classes = CategoryServiceTest.class)
class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	ArrayList<Category> mydata;

	@Test
	@Order(1)
	 void test_getAllCatagories() {
		ArrayList<Category> mydata = new ArrayList<Category>();

		Category category1 = new Category("FOOD", "Y", "Admin", "Admin");
		Category category2 = new Category("MEDICAL", "N", "Admin", "Admin");
		Category category3 = new Category("CHAILD", "N", "Admin", "Admin");

		mydata.add(category1);
		mydata.add(category2);
		mydata.add(category3);

		when(categoryRepository.findAll()).thenReturn(mydata); // Mocking

		assertEquals(3, categoryServiceImpl.getAllCatagories().size());

	}

	@Test
	@Order(2)
	 void test_getCategoryById() throws Exception {
		Integer id = 1;
		Category category = new Category("FOOD", "Y", "Admin", "Admin");
		category.setCategoryId(id);
		when(categoryRepository.findById(id)).thenReturn(Optional.of(category)); // Mock
		assertEquals(id, categoryServiceImpl.getCategoryById(id).getCategoryId());

	}

	@Test
	@Order(3)
	 void test_creatNewCategory() {
		Category category = new Category("QUALIFIED", "N", "Admin", "Admin");
		when(categoryRepository.save(category)).thenAnswer(invocation -> {
			Category savedCategory = invocation.getArgument(0);
			savedCategory.setCategoryId(4); // Simulate auto-generation of ID
			return savedCategory;
		});

		assertEquals("Record Created Success Fully :4", categoryServiceImpl.createCategory(category));
	}

	@Test
	@Order(4)
	 void test_createCategory_alreadyExists() {
		// Arrange
		Integer id = 2;
		Category category = new Category("QUALIFIED", "N", "Admin", "Admin");
		category.setCategoryId(id); // Manually setting ID
		// Assert
		assertEquals("Record Already Created  :" + id, categoryServiceImpl.createCategory(category));
	}

	@Test
	@Order(5)
	 void test_UpdateCategory() throws Exception {
		// Arrange
		Category category = new Category("QUALIFIED", "Y", "Admin", "Admin");
		Category existingCategory = new Category("QUALIFIED", "N", "User", "User");
		existingCategory.setCategoryId(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory));
		when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

		// Assert
		assertEquals("Updated Record Success Fully :1", categoryServiceImpl.updateCategoryById(category, 1));
		verify(categoryRepository).save(existingCategory);
	}

	@Test
	@Order(6)
	 void test_DeleteCategoryById() throws Exception {
		// Arrange
		Category category = new Category("QUALIFIED", "Y", "Admin", "Admin");
		category.setCategoryId(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
		// Assert
		assertEquals("Record Deleted Successfully: " + category, categoryServiceImpl.deleteCategoryById(1));
		verify(categoryRepository).deleteById(1);
	}

	@Test
	@Order(7)
	 void test_CategoryStatusChange() {
		// Arrange
		Category category = new Category("QUALIFIED", "Y", "Admin", "Admin");
		category.setCategoryId(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

		// Assert
		assertTrue(categoryServiceImpl.categoryStatusChange(1, "N"));
		assertEquals("N", category.getActiveSwitch());
		verify(categoryRepository).save(category);
	}

}

//Category category1 = new Category();
//category1.setCategoryName("Food");
//category1.setActiveSwitch("Y");
//category1.setCreatedBy("System");
//category1.setUpdatedBy("System");
//
//Category category2 = new Category();
//category2.setCategoryName("Medical");
//category2.setActiveSwitch("N");
//category2.setCreatedBy("Admin");
//category2.setUpdatedBy("Admin");
