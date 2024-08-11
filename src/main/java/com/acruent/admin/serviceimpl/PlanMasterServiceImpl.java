package com.acruent.admin.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acruent.admin.entity.PlanMaster;
import com.acruent.admin.exceptionhandle.IdNotFoundException;
import com.acruent.admin.repository.PlanMasterRepository;
import com.acruent.admin.service.PlanMasterService;

@Service
public class PlanMasterServiceImpl implements PlanMasterService {
	private PlanMasterRepository planMasterRepository;

	public PlanMasterServiceImpl(PlanMasterRepository planMasterRepository) {
		this.planMasterRepository = planMasterRepository;
	}

	@Override
	public String createPlan(PlanMaster planMaster) {
		Integer planId = planMaster.getPlanId();
		if (planId == null) {
			PlanMaster newPlan = planMasterRepository.save(planMaster);
			if (newPlan != null && newPlan.getPlanId() != null) {
				return "Record Created Success Fully :" + newPlan.getPlanId();
			} else {
				return "Record Creation Faild" + newPlan.getPlanId();
			}
		} else {
			return "Record Already Created  :" + planMaster.getPlanId();

		}

	}

	@Override
	public PlanMaster getPlanById(Integer id) throws Exception {
		Optional<PlanMaster> findById = planMasterRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		throw new IdNotFoundException("Plan ID not found: " + id);
	}

	@Override
	public List<PlanMaster> getAllPlans() {
		List<PlanMaster> findAll = planMasterRepository.findAll();
		return findAll;
	}

	@Override
	public String updatePlanById(PlanMaster planMaster, Integer id) throws Exception {
		PlanMaster existPlan = planMasterRepository.findById(id).orElse(null);
		if (existPlan != null) {
			existPlan.setPlanName(planMaster.getPlanName());
			existPlan.setActiveSwitch(planMaster.getActiveSwitch());
			existPlan.setPlanStartedDate(planMaster.getPlanStartedDate());
			existPlan.setPlanEndedDate(planMaster.getPlanEndedDate());
			existPlan.setPlanCategegoryId(planMaster.getPlanCategegoryId());
			existPlan.setCreatedBy(planMaster.getCreatedBy());
			existPlan.setUpdateBy(planMaster.getUpdateBy());
            planMasterRepository.save(existPlan);
			return "Updated Record Success Fully :" + existPlan.getPlanId();

		}
		throw new IdNotFoundException("NoIdException :" + planMaster.getPlanId());
	}

	@Override
	public String deletePlanById(Integer id) throws Exception {
		Optional<PlanMaster> findById = planMasterRepository.findById(id);
		if (findById.isPresent()) {
			planMasterRepository.deleteById(id);
			return "Record Deleted Successfully:"+findById.get().toString(); // Ensure PlanMaster has a meaningful toString
																		// method
		}
		throw new IdNotFoundException("No ID found for deletion: " + id);
	}

	@Override
	public boolean planStatusChange(Integer masterPlanId, String status) {
		Optional<PlanMaster> findById = planMasterRepository.findById(masterPlanId);
		if (findById.isPresent()) {
			PlanMaster planMaster = findById.get();
			planMaster.setActiveSwitch(status != null ? status : "DefultValue");
			planMasterRepository.save(planMaster);
			return true;
		}
		return false;
	}

}
