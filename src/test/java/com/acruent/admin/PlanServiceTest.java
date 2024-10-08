package com.acruent.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.acruent.admin.entity.PlanMaster;
import com.acruent.admin.exceptionhandle.PlanNotFoundException;
import com.acruent.admin.repository.PlanMasterRepository;
import com.acruent.admin.serviceimpl.PlanMasterServiceImpl;

@SpringBootTest(classes = PlanServiceTest.class)
class PlanServiceTest {

	@Mock
	PlanMasterRepository planMasterRepository;
	@InjectMocks
	PlanMasterServiceImpl planMasterServiceImpl;

	@Test
	@Order(1)
	void test_getAllPlans() {

		ArrayList<PlanMaster> listOfPlans = new ArrayList<PlanMaster>();

		PlanMaster plan1 = new PlanMaster("SNAP", LocalDate.of(2024, 7, 3), LocalDate.of(2025, 7, 2), 1, "Y", "Admin",
				"Admin");

		PlanMaster plan2 = new PlanMaster("CCP", LocalDate.of(2024, 12, 25), LocalDate.of(2023, 12, 24), 2, "N",
				"Admin", "Admin");

		listOfPlans.add(plan1);
		listOfPlans.add(plan2);

		when(planMasterRepository.findAll()).thenReturn(listOfPlans); // Mocking

		assertEquals(2, planMasterServiceImpl.getAllPlans().size());

	}

	@Test
	@Order(2)
	void test_getPlanById() throws Exception {
		Integer planId = 3;
		PlanMaster plan3 = new PlanMaster("QHP", LocalDate.of(2024, 8, 3), LocalDate.of(2025, 8, 2), 1, "Y", "Admin",
				"Admin");
		plan3.setPlanId(planId);

		when(planMasterRepository.findById(planId)).thenReturn(Optional.of(plan3)); // Mock
		assertEquals(planId, planMasterServiceImpl.getPlanById(planId).getPlanId());

	}

	@Test
	@Order(3)
	void test_getPlanById_NotFound() {
		// Arrange
		Integer planId = 1;

		// Simulate that the plan is not found
		when(planMasterRepository.findById(planId)).thenReturn(Optional.empty());

		// Act & Assert
		PlanNotFoundException exception = assertThrows(PlanNotFoundException.class,
				() -> planMasterServiceImpl.getPlanById(planId));

		assertEquals("Plan ID not found: " + planId, exception.getMessage());
		verify(planMasterRepository).findById(planId);
	}

	@Test
	@Order(4)
	void test_createPlan() {
		PlanMaster plan4 = new PlanMaster("QHP", LocalDate.of(2024, 8, 3), LocalDate.of(2025, 8, 2), 1, "Y", "Admin",
				"Admin");
		when(planMasterRepository.save(plan4)).thenAnswer(invocation -> {
			PlanMaster argument = invocation.getArgument(0);
			argument.setPlanId(4); // Simulate auto-generation of ID
			return argument;
		});

		assertEquals("Record Created Success Fully :4", planMasterServiceImpl.createPlan(plan4));

	}

	@Test
	@Order(5)
	void test_CreatePlan_Failure_NoIdGenerated() {
		// Arrange
		PlanMaster planMaster = new PlanMaster();
		when(planMasterRepository.save(planMaster)).thenReturn(new PlanMaster());

		// Act
		String result = planMasterServiceImpl.createPlan(planMaster);

		// Assert
		assertEquals("Record Creation Faildnull", result);
		verify(planMasterRepository).save(planMaster);
	}

	@Test
	@Order(6)
	void test_CreatePlan_AlreadyExists() {
		// Arrange
		PlanMaster planMaster = new PlanMaster();
		planMaster.setPlanId(1);

		// Act
		String result = planMasterServiceImpl.createPlan(planMaster);

		// Assert
		assertEquals("Record Already Created  :1", result);
		verify(planMasterRepository, never()).save(planMaster);
	}

	@Test
	@Order(7)
	void test_updatePlanById() throws Exception {
		PlanMaster existingPlan = new PlanMaster("QHP", LocalDate.of(2024, 8, 3), LocalDate.of(2025, 8, 2), 1, "Y",
				"Admin", "Admin");
		existingPlan.setPlanId(4);

		PlanMaster plan5 = new PlanMaster("MEDICAL", LocalDate.of(2024, 8, 3), LocalDate.of(2025, 8, 2), 1, "Y",
				"Admin", "Admin");

		when(planMasterRepository.findById(4)).thenReturn(Optional.of(existingPlan));
		when(planMasterRepository.save(existingPlan)).thenReturn(existingPlan);
		assertEquals("Updated Record Success Fully :4", planMasterServiceImpl.updatePlanById(plan5, 4));

		planMasterRepository.save(existingPlan); // Optional
	}

	@Test
	@Order(8)
	void test_UpdatePlanById_NotFound() {
		// Arrange
		Integer planId = 1;
		PlanMaster planMaster = new PlanMaster();
		planMaster.setPlanId(planId);

		// Simulate that the plan is not found
		when(planMasterRepository.findById(planId)).thenReturn(Optional.empty());

		// Act & Assert
		PlanNotFoundException exception = assertThrows(PlanNotFoundException.class,
				() -> planMasterServiceImpl.updatePlanById(planMaster, planId));

		assertEquals("NoIdException :" + planMaster.getPlanId(), exception.getMessage());
		verify(planMasterRepository).findById(planId);
	}

	@Test
	@Order(9)
	void test_deletePlanById() throws Exception {
		PlanMaster plan5 = new PlanMaster("MEDICAL", LocalDate.of(2024, 8, 3), LocalDate.of(2025, 8, 2), 1, "Y",
				"Admin", "Admin");
		plan5.setPlanId(5);

		when(planMasterRepository.findById(5)).thenReturn(Optional.of(plan5));

		assertEquals("Record Deleted Successfully:" + plan5, planMasterServiceImpl.deletePlanById(5));

		planMasterRepository.deleteById(5);
	}

	@Test
	@Order(10)
	void test_DeletePlanById_NotFound() {
		// Arrange
		Integer planId = 1;

		// Simulate that the plan is not found
		when(planMasterRepository.findById(planId)).thenReturn(Optional.empty());

		// Act & Assert
		PlanNotFoundException exception = assertThrows(PlanNotFoundException.class,
				() -> planMasterServiceImpl.deletePlanById(planId));

		assertEquals("No ID found for deletion: " + planId, exception.getMessage());
		verify(planMasterRepository).findById(planId);
		verify(planMasterRepository, never()).deleteById(planId);
	}

	@Test
	@Order(11)
	void test_planStatusChange() {
		PlanMaster plan5 = new PlanMaster("MEDICAL", LocalDate.of(2024, 8, 3), LocalDate.of(2025, 8, 2), 1, "Y",
				"Admin", "Admin");
		plan5.setPlanId(5);

		when(planMasterRepository.findById(5)).thenReturn(Optional.of(plan5));

		assertTrue(planMasterServiceImpl.planStatusChange(5, "N"));
		assertEquals("N", plan5.getActiveSwitch());
		planMasterRepository.save(plan5);
	}

}
