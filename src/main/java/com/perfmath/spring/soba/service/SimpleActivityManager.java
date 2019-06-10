package com.perfmath.spring.soba.service;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.dao.ActivityDao;
import com.perfmath.spring.soba.model.domain.Activity;

public class SimpleActivityManager implements ActivityManager {

    private ActivityDao activityDao;

    public List<Activity> getActivities(String customerId, String accountType)
    {
    	return activityDao.getActivities(customerId, accountType);
    }
    public List<Activity> getActivities(String customerId, String accountType,
    		String periodInDays) {
    	return activityDao.getActivities(customerId, accountType, periodInDays);
    }
    public List<Activity> getActivities(String customerId, String accountType,
    		String fromDate, String toDate) {
    	return activityDao.getActivities(customerId, accountType, fromDate, toDate);
    }
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
}
