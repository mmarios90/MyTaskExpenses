package com.mytask.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "activity")
public class Activity {
	
	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", title=" + title + ", itemCode=" + itemCode + ", totalCost="
				+ totalCost + "]";
	}

	@Id
	@Column(name = "activityid")
	private BigInteger activityId;
	
	@Column(name = "title")
    private String title;
	
	@Column(name = "itemcode")
	private String itemCode;
	
	@Column(name = "totalcost")
    private BigDecimal totalCost;
	
	public Activity(BigInteger activityId, String title, String itemCode, BigDecimal totalCost) {
		this.activityId = activityId;
		this.title = title;
		this.itemCode = itemCode;
		this.totalCost = totalCost;
	}

	public Activity() {
	}

	//getters and setters
	public BigInteger getActivityId() {
		return activityId;
	}

	public void setActivityId(BigInteger activityId) {
		this.activityId = activityId;
	}
		
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String name) {
		this.title = name;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
}


