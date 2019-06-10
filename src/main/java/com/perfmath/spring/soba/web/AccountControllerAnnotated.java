package com.perfmath.spring.soba.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perfmath.spring.soba.service.AccountManager;

@Controller
public class AccountControllerAnnotated {

    private AccountManager accountManager;
    @Autowired
    public AccountControllerAnnotated(AccountManager accountManager) {
        this.accountManager = accountManager;
    }
    @RequestMapping("/updateAccountBalance.htm")
	public String updateAccountBalance (
            @RequestParam("accountId") String accountId,
            @RequestParam("amount") double amount,
            ModelMap model) {
        double balance = accountManager.updateAccountBalance(amount, accountId);
        model.addAttribute("accountId", accountId);
        model.addAttribute("balance", balance);
        return "success";
    }
}
