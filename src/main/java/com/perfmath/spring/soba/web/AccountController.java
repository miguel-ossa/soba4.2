package com.perfmath.spring.soba.web;

import com.perfmath.spring.soba.service.AccountManager;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class AccountController implements Controller {

    private AccountManager accountManager;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new java.util.Date()).toString();

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("accounts", this.accountManager.getAccounts());
        
   	
        return new ModelAndView("accountList", "model", myModel);
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

}