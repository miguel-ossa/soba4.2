package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.Activity;

public interface ActivityManager extends Serializable{
    public List<Activity> getActivities(String customerID, String accountType);
    public List<Activity> getActivities(String customerID, String accountType,
    		String periodInDays);
    public List<Activity> getActivities(String customerID, String accountType,
    		String fromDate, String toDate);
}
