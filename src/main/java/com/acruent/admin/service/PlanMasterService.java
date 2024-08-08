package com.acruent.admin.service;

import java.util.List;

import com.acruent.admin.entity.PlanMaster;

public interface PlanMasterService
{
	public String createPlan(PlanMaster planMaster);
	public PlanMaster getPlanById(Integer id) throws Exception;
	public List<PlanMaster> getAllPlans();
	public String updatePlanById(PlanMaster planMaster, Integer id) throws Exception;
	public String deletePlanById(Integer id) throws Exception;
    public  boolean planStatusChange(Integer masterPlanId,String status);


}
