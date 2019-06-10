package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.service.AccountManager;

@ContextConfiguration(locations = "/beans-test-app-context.xml")
public class AccountManagerJUnit4ContextAbstractTest extends
        AbstractJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 500.0;
    private static final double TEST_AMOUNT = 300.0;
    private AccountManager accountManager;

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
