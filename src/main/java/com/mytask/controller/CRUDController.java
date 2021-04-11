package com.mytask.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;

import com.mytask.entity.Activity;
import com.mytask.entity.Labor;

public class CRUDController {

	public List<Activity> fetchRowsActivity(Session session, String query) {
		return session.createQuery(query).getResultList();

	}

	public List<Labor> fetchRowsLabor(Session session, String query) {
		return session.createQuery(query).getResultList();
	}

	public List<Labor> fetchRowsLaborByActivityId(Session session, String query, BigInteger activityId) {
		return session.createQuery(query).setParameter("activityIdColumn", activityId).getResultList();
	}

	public void SetNewLaborCost(Session session, String query, BigInteger laborId, BigDecimal totalCost) 
	{
		session.createQuery(query).setParameter("newValue",
				totalCost).setParameter("thisLabor", laborId).executeUpdate(); 
	}
	
	public BigDecimal getLaborCostSum(Session session, String query, BigInteger activityId) {
		return (BigDecimal) session.createQuery(query).setParameter("activityIdColumn", activityId).getSingleResult();
	}
	
	public void setActivityTotalCoast(Session session, String query, BigInteger activityId, BigDecimal totalCost) {
		session.createQuery(query).setParameter("totalCostSum", totalCost)
		.setParameter("activityIdColumn", activityId).executeUpdate();
	}

}
