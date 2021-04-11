package com.mytask.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "laborer")
public class Labor {

	@Id
	@Column(name = "laborerid")
	private BigInteger laborId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "itemcode")
	private String itemCode;
	
	@Column(name = "totalcost")
	private BigDecimal totalCost;
	
	@Column(name = "activityid")
	private BigInteger activityId;
	
	public Labor (BigInteger laborId, String title, String itemCode, BigDecimal totalCost, BigInteger activityId) {
		this.laborId = laborId;
		this.title = title;
		this.itemCode = itemCode;
		this.totalCost = totalCost;
		this.setActivityId(activityId);
	}

	public Labor() {
	}

	//getters and setters
	public BigInteger getLaborId() {
		return laborId;
	}

	public void setLaborId(BigInteger laborId) {
		this.laborId = laborId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public BigInteger getActivityId() {
		return activityId;
	}

	public void setActivityId(BigInteger activityId) {
		this.activityId = activityId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Labor [laborId=");
		builder.append(laborId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", itemCode=");
		builder.append(itemCode);
		builder.append(", totalCost=");
		builder.append(totalCost);
		builder.append(", activityId=");
		builder.append(activityId);
		builder.append("]");
		return builder.toString();
	}

	
}
