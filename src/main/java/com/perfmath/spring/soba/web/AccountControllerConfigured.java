package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.perfmath.spring.soba.service.AccountManager;

public class AccountControllerConfigured extends AbstractController{

    private AccountManager accountManager;

    public AccountControllerConfigured(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

	public ModelAndView handleRequestInternal (HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String accountId =
        		ServletRequestUtils.getRequiredStringParameter (request, "accountId");
        double amount =
        		ServletRequestUtils.getRequiredDoubleParameter (request, "amount");
        double balance = accountManager.updateAccountBalance (amount, accountId);
        return new ModelAndView ("success", "accountId", accountId).addObject("balance", balance);
    }
}
