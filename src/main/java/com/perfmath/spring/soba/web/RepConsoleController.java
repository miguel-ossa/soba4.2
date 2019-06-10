package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.service.ActivityManager;
import com.perfmath.spring.soba.service.CustomerManager;

@Controller
@RequestMapping("/repConsole")
@SessionAttributes("rep")
public class RepConsoleController extends AbstractController {

	private ActivityManager activityManager;
	private CustomerManager customerManager;
	private AccountManager accountManager;

	@Autowired
	public RepConsoleController(ActivityManager activityManager,
			CustomerManager customerManager, AccountManager accountManager) {
		this.customerManager = customerManager;
		this.activityManager = activityManager;
		this.accountManager = accountManager;

	}

	public void setActivityManager(ActivityManager activityManager) {
		this.activityManager = activityManager;
	}

	public ActivityManager getActivityManager() {
		return activityManager;
	}
	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getModelAndView(request);
	}
		public ModelAndView getModelAndView(HttpServletRequest request) {
		
		String customerId = request.getParameter("customerId");
		String accountType = request.getParameter("accountType");
		String period = request.getParameter("period");
		String periodInDays = request.getParameter("periodInDays");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		
		if (accountType == null) accountType = "Checking";
		ModelAndView mav = null;
		if (period == null) {
			mav = new ModelAndView ("activityList", "activities",
					activityManager.getActivities(customerId, accountType, "30"));
		} else if (period.equals ("relative")) {
			mav = new ModelAndView ("activityList", "activities",
					activityManager.getActivities(customerId, accountType, periodInDays));
		} else if (period.equals ("absolute")) {
			mav = new ModelAndView ("activityList", "activities",
					activityManager.getActivities(customerId, accountType, fromDate, toDate));
		} 
		mav.addObject("customerId", customerId);
		mav.addObject("accountId", accountManager.getAccountIdByCustomerId(customerId, accountType));
		return mav;
	}

	public CustomerManager getcustomerManager() {
		return customerManager;
	}

	public void setcustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

}
