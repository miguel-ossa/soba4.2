package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.model.domain.Account;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans-test-app-context.xml")
public class AccountManagerJUnit4ContextAwareTest implements
        ApplicationContextAware {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 500.0;
    private static final double TEST_AMOUNT = 300.0;
    private ApplicationContext applicationContext;
    private AccountManager accountManager;

    public void setApplicationContext (ApplicationContext applicationContext ) {
    	this.applicationContext = applicationContext;
    }
    
    @Before
    public void init() {
        accountManager = (AccountManager) applicationContext.getBean("accountManager");
    }

    @Test
    public void createAccount () {
        Account account = new Account ();
        account.setAccountId(TEST_ACCOUNT_ID);
        account.setBalance(INITIAL_BALANCE);
        accountManager.createAccount(account);
        assertEquals(accountManager.getBalance(TEST_ACCOUNT_ID), INITIAL_BALANCE, 0);
    }

    @Test
    public void updateAccountBalance () {
        accountManager.updateAccountBalance (TEST_AMOUNT, TEST_ACCOUNT_ID);
        assertEquals(accountManager.getBalance(TEST_ACCOUNT_ID), 
        		(INITIAL_BALANCE + TEST_AMOUNT), 0);
    }
}
