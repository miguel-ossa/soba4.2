package com.perfmath.spring.soba.testing.testng;

import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.service.AccountManager;

@ContextConfiguration(locations = "/beans-test-tx.xml")
public class AccountManagerTestNGContextFixtureAbstractTest extends AbstractTestNGSpringContextTests{

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 500.0;
    private static final double TEST_AMOUNT = 300.0;
    @Autowired
    private AccountManager accountManager;
    
    @BeforeMethod
    public void init() {
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
