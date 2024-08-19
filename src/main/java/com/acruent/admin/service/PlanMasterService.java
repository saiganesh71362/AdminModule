package com.acruent.admin.service;

import java.util.List;

import com.acruent.admin.entity.PlanMaster;
import com.acruent.admin.exceptionhandle.IdNotFoundException;
import com.acruent.admin.exceptionhandle.PlanNotFoundException;

public interface PlanMasterService
{
	public String createPlan(PlanMaster planMaster);
	public PlanMaster getPlanById(Integer id) throws PlanNotFoundException;
	public List<PlanMaster> getAllPlans();
	public String updatePlanById(PlanMaster planMaster, Integer id) throws IdNotFoundException;
	public String deletePlanById(Integer id) throws IdNotFoundException;
    public  boolean planStatusChange(Integer masterPlanId,String status);


}
