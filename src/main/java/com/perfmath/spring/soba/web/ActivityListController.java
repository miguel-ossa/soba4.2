package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;

import com.perfmath.spring.soba.model.domain.Activity;
import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.service.ActivityManager;
import com.perfmath.spring.soba.service.LoginUserManager;

@Controller
@RequestMapping("/activityList")
@SessionAttributes("activity")
public class ActivityListController extends AbstractController {
	private ActivityManager activityManager;
	private LoginUserManager loginUserManager;
	private AccountManager accountManager;
	@Autowired
	public ActivityListController(ActivityManager activityManager,
			LoginUserManager loginUserManager, AccountManager accountManager) {
		this.loginUserManager = loginUserManager;
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

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String customerId = request.getParameter("customerId");
		String accountId = request.getParameter("accountId");
		String authority = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().toString();

		if (customerId == null && authority.contains("ROLE_CUST")) {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();

			customerId = loginUserManager.getCustomerIdByUsername(username);
		}

		String accountType = "Checking";
		if (request.getParameter("accountType") != null) {
			accountType = request.getParameter("accountType");
		}
		if (accountId == null || accountId.isEmpty()) {
			accountId = accountManager.getAccountIdByCustomerId(customerId, accountType);
		}
		List<Activity> activities = activityManager.getActivities(customerId, accountType);
		ModelAndView mav = new ModelAndView ();
		mav.setViewName ("activityList");
		mav.addObject("customerId", customerId);
		mav.addObject ("accountId", accountId);
		mav.addObject("activities", activities);
		return mav;
	}

	public LoginUserManager getLoginUserManager() {
		return loginUserManager;
	}

	public void setLoginUserManager(LoginUserManager loginUserManager) {
		this.loginUserManager = loginUserManager;
	}

}
