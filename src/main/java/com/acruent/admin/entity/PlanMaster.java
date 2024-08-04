package com.acruent.admin.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PLAN_TABLE")
public class PlanMaster
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private LocalDate planStartedDate;
	private LocalDate planEndedDate;
	private Integer planCategegoryId;
	private String activeSwitch;
	// audit columns
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedDate;
	private String createdBy;
	private String updateBy;
	
	
	public PlanMaster() {}
	public PlanMaster(String planName, LocalDate planStartedDate, LocalDate planEndedDate, Integer planCategegoryId,
			String activeSwitch, LocalDateTime createdDate, LocalDateTime updatedDate, String createdBy,
			String updateBy) {
		this.planName = planName;
		this.planStartedDate = planStartedDate;
		this.planEndedDate = planEndedDate;
		this.planCategegoryId = planCategegoryId;
		this.activeSwitch = activeSwitch;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updateBy = updateBy;
	}
	
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public LocalDate getPlanStartedDate() {
		return planStartedDate;
	}
	public void setPlanStartedDate(LocalDate planStartedDate) {
		this.planStartedDate = planStartedDate;
	}
	public LocalDate getPlanEndedDate() {
		return planEndedDate;
	}
	public void setPlanEndedDate(LocalDate planEndedDate) {
		this.planEndedDate = planEndedDate;
	}
	public Integer getPlanCategegoryId() {
		return planCategegoryId;
	}
	public void setPlanCategegoryId(Integer planCategegoryId) {
		this.planCategegoryId = planCategegoryId;
	}
	public String getActiveSwitch() {
		return activeSwitch;
	}
	public void setActiveSwitch(String activeSwitch) {
		this.activeSwitch = activeSwitch;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	@Override
	public String toString() {
		return "PlanMaster [planId=" + planId + ", planName=" + planName + ", planStartedDate=" + planStartedDate
				+ ", planEndedDate=" + planEndedDate + ", planCategegoryId=" + planCategegoryId + ", activeSwitch="
				+ activeSwitch + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy="
				+ createdBy + ", updateBy=" + updateBy + "]";
	}
	
	
}
