package com.acruent.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acruent.admin.controller.PlanController;
import com.acruent.admin.entity.PlanMaster;
import com.acruent.admin.serviceimpl.PlanMasterServiceImpl;

@SpringBootTest
class PlanControllerTest {
	@Mock
	PlanMasterServiceImpl planMasterServiceImpl;

	@InjectMocks
	PlanController planController;

	@Test
	@Order(1)
	void test_createNewPlan() {
		Integer id = 1;
		PlanMaster plan1 = new PlanMaster("SNAP", LocalDate.of(2024, 7, 3), LocalDate.of(2025, 7, 2), 1, "Y", "Admin",
				"Admin");
		plan1.setPlanId(id);

		when(planMasterServiceImpl.createPlan(plan1)).thenReturn("SNAP");
		ResponseEntity<String> createNewPlan = planController.createNewPlan(plan1);

		assertEquals(HttpStatus.CREATED, createNewPlan.getStatusCode());
		assertEquals("SNAP", createNewPlan.getBody());

	}

	@Test
	@Order(2)
	void test_getPlanById() throws Exception {
		Integer id = 2;
		PlanMaster plan2 = new PlanMaster("CCP", LocalDate.of(2024, 12, 25), LocalDate.of(2023, 12, 24), 2, "N",
				"Admin", "Admin");
		plan2.setPlanId(id);

		when(planMasterServiceImpl.getPlanById(id)).thenReturn(plan2);

		ResponseEntity<PlanMaster> planById = planController.getPlanById(id);

		assertEquals(HttpStatus.FOUND, planById.getStatusCode());
		assertEquals(plan2, planById.getBody());
	}

	@Test
	@Order(3)
	void test_getAllPlans() {
		ArrayList<PlanMaster> listOfPlans = new ArrayList<PlanMaster>();

		PlanMaster plan1 = new PlanMaster("SNAP", LocalDate.of(2024, 7, 3), LocalDate.of(2025, 7, 2), 1, "Y", "Admin",
				"Admin");

		PlanMaster plan2 = new PlanMaster("CCP", LocalDate.of(2024, 12, 25), LocalDate.of(2023, 12, 24), 2, "N",
				"Admin", "Admin");

		listOfPlans.add(plan1);
		listOfPlans.add(plan2);

		when(planMasterServiceImpl.getAllPlans()).thenReturn(listOfPlans); // Mock

		ResponseEntity<List<PlanMaster>> allPlans = planController.getAllPlans();

		assertEquals(HttpStatus.FOUND, allPlans.getStatusCode());
		assertEquals(2, allPlans.getBody().size());

	}

	@Test
	@Order(4)
	void test_updatePlanById() throws Exception {
		Integer id = 2;
		PlanMaster plan2 = new PlanMaster("CCP", LocalDate.of(2024, 12, 25), LocalDate.of(2023, 12, 24), 2, "N",
				"Admin", "SAIGANESH");
		plan2.setPlanId(id);

		when(planMasterServiceImpl.updatePlanById(plan2, id)).thenReturn("SAIGANESH");

		ResponseEntity<String> updatePlanById = planController.updatePlanById(plan2, id);

		assertEquals(HttpStatus.OK, updatePlanById.getStatusCode());
		assertEquals("SAIGANESH", updatePlanById.getBody());
	}

	@Test
	@Order(5)
	void test_deletePlanById() throws Exception {
		Integer id = 2;
		PlanMaster plan2 = new PlanMaster("CCP", LocalDate.of(2024, 12, 25), LocalDate.of(2023, 12, 24), 2, "N",
				"Admin", "SAIGANESH");
		plan2.setPlanId(id);

		when(planMasterServiceImpl.deletePlanById(id)).thenReturn("CCP");

		ResponseEntity<String> deletePlanById = planController.deletePlanById(id);

		assertEquals(HttpStatus.ACCEPTED, deletePlanById.getStatusCode());
		assertEquals("CCP", deletePlanById.getBody());
	}

	@Test
	@Order(6)
	void test_categoryStatusChange() {
		Integer id = 2;
		PlanMaster plan2 = new PlanMaster("CCP", LocalDate.of(2024, 12, 25), LocalDate.of(2023, 12, 24), 2, "N",
				"Admin", "SAIGANESH");
		plan2.setPlanId(id);

		when(planMasterServiceImpl.planStatusChange(id, "Y")).thenReturn(true);
		ResponseEntity<String> categoryStatusChange = planController.categoryStatusChange("Y", id);

		assertEquals(HttpStatus.OK, categoryStatusChange.getStatusCode());
		assertEquals("Successfully updated status for Plan ID: 2", categoryStatusChange.getBody());
		planMasterServiceImpl.planStatusChange(id, "Y");
	}

}
