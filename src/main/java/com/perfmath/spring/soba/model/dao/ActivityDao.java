package com.perfmath.spring.soba.model.dao;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.Activity;

public interface ActivityDao {
	public List<Activity> getActivities(String customerId, String accountType);
	public List<Activity> getActivities(String customerId, String accountType,
			String periodInDays);
	public List<Activity> getActivities(String customerId, String accountType,
			String fromDate, String toDate);
	/*
    public List<Activity> getByCustomerID(String customerId);
    public List<Activity> getByAccountID(String accountId);
    */
    public List<Map<String, Object>> findAll();
    public int countAll();

}
