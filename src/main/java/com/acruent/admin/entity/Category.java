package com.acruent.admin.entity;

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
@Table(name =  "CATEGORY_TABLE")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryName;
	private String activeSwitch;
	// Audit Columns
	// created by updated by skip
	@CreationTimestamp
	@Column(updatable  = false)
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedDate;
	private String createdBy;
	private String updatedBy;
	
	

	public Category(String categoryName, String activeSwitch, LocalDateTime createdDate, LocalDateTime updatedDate,
			String createdBy, String updatedBy) {
		this.categoryName = categoryName;
		this.activeSwitch = activeSwitch;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public Category() {}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", activeSwitch="
				+ activeSwitch + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
	
	
	

	
}
