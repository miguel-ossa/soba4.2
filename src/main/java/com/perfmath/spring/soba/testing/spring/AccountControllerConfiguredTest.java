package com.perfmath.spring.soba.testing.spring;

import static org.springframework.test.web.ModelAndViewAssert.*;

import static org.easymock.EasyMock.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.web.AccountControllerConfigured;

public class AccountControllerConfiguredTest {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double TEST_AMOUNT = 100.0;
    private static final double NEW_BALANCE = 200.0;
    
    private AccountManager mock;
    private AccountControllerConfigured accountController;

    @BeforeMethod
    public void init() {
    	mock = createMock (AccountManager.class);
        accountController = new AccountControllerConfigured(mock);
    }

    @Test
    public void updateAccountBalance() throws Exception {
    	MockHttpServletRequest request = new MockHttpServletRequest ();
    	request.setMethod ("POST");
    	request.addParameter ("accountId", TEST_ACCOUNT_ID);
    	request.addParameter ("amount", String.valueOf(TEST_AMOUNT));
    	MockHttpServletResponse response = new MockHttpServletResponse ();

        expect (mock.updateAccountBalance(TEST_AMOUNT, TEST_ACCOUNT_ID)).andReturn(NEW_BALANCE);
        replay(mock);

       ModelAndView modelAndView = 
    		   accountController.handleRequest (request, response);
       verify (mock);
       
       assertViewName (modelAndView, "success");
       assertModelAttributeValue (modelAndView, "accountId", TEST_ACCOUNT_ID);
       assertModelAttributeValue (modelAndView, "balance", NEW_BALANCE);
    }
}
