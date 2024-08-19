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

import com.acruent.admin.entity.PlanMaster;
import com.acruent.admin.exceptionhandle.IdNotFoundException;
import com.acruent.admin.service.PlanMasterService;

@RestController
@RequestMapping("/plan")
public class PlanController {
	private static final Logger logger = LogManager.getLogger(PlanController.class);

	private PlanMasterService planMasterService;

	public PlanController(PlanMasterService planMasterService) {
		this.planMasterService = planMasterService;
	}

	@PostMapping("/newPlanCreate")
	public ResponseEntity<String> createNewPlan(@RequestBody PlanMaster planMaster) {
		logger.info("Request received to create a new Plan: {}", planMaster);
		String createPlan = planMasterService.createPlan(planMaster);
		logger.info("Plan created successfully: {}", createPlan);
		return new ResponseEntity<>(createPlan, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanMaster> getPlanById(@PathVariable Integer id) throws IdNotFoundException {
		logger.info("Received request to get Plan by ID: {}", id);
		PlanMaster planById = planMasterService.getPlanById(id);
		logger.info("Successfully retrieved Plan with ID: {}", id);
		return new ResponseEntity<>(planById, HttpStatus.FOUND);

	}

	@GetMapping("/allPlans")
	public ResponseEntity<List<PlanMaster>> getAllPlans() {
		logger.info("Received request to get all categories");
		List<PlanMaster> allPlans = planMasterService.getAllPlans();
		logger.info("Successfully retrieved {} categories", allPlans.size());
		return new ResponseEntity<>(allPlans, HttpStatus.FOUND);
	}

	@PutMapping("/updatePlan/{id}")
	public ResponseEntity<String> updatePlanById(@RequestBody PlanMaster planMaster, @PathVariable Integer id)
			throws IdNotFoundException {
		logger.info("Received request to update Category with ID: {}", id);
		logger.debug("Category details: {}", planMaster);
		String updatePlanById = planMasterService.updatePlanById(planMaster, id);
		logger.info("Successfully updated Category with ID: {}", id);

		return new ResponseEntity<>(updatePlanById, HttpStatus.OK);
	}

	@DeleteMapping("/deletePlan/{id}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer id) throws IdNotFoundException {
		logger.info("Received request to delete Plan with ID: {}", id);
		String response = planMasterService.deletePlanById(id);
		logger.info("Successfully deleted Plan with ID: {}", id);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/categoryStatusChange/{status}/{id}")
	public ResponseEntity<String> categoryStatusChange(@PathVariable String status, @PathVariable Integer id) {
		logger.info("Received request to change status of Category with ID: {} to status: {}", id, status);
		boolean categoryStatusChanged = planMasterService.planStatusChange(id, status);
		String msg;

		if (categoryStatusChanged) {
			msg = "Successfully updated status for Plan ID: " + id;
			logger.info(msg);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			msg = "Failed to update status for Plan ID: " + id;
			logger.warn(msg);
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

		}
	}

}
