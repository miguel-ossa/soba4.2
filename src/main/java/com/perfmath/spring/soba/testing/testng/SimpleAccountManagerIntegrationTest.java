package com.perfmath.spring.soba.testing.testng;

import static org.testng.Assert.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.model.dao.AccountDao;
import com.perfmath.spring.soba.service.SimpleAccountManager;
import com.perfmath.spring.soba.testing.InMemoryAccountDao;

public class SimpleAccountManagerIntegrationTest {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 200.0;
    private static final double UPDATE_AMOUNT = 50.0;
    private AccountDao accountDao;
    private SimpleAccountManager accountManager;
    private Account account;

    @BeforeMethod
    public void init() {
        account = new Account ();
        account.setAccountId (TEST_ACCOUNT_ID);
        account.setBalance (200.0);
        
        accountManager = new SimpleAccountManager ();
        accountDao = new InMemoryAccountDao();
        accountManager.setAccountDao (accountDao);
    }

    @Test
    public void createAccount () {
        accountManager.createAccount(account);
        assertEquals((accountDao.findByAccountID(TEST_ACCOUNT_ID)).getAccountId(), TEST_ACCOUNT_ID);
        assertEquals((accountDao.findByAccountID(TEST_ACCOUNT_ID)).getBalance(), INITIAL_BALANCE, 0);
    }
 
    @Test
    public void updateAccountBalance () {
      accountManager.createAccount(account);
      accountManager.updateAccountBalance(UPDATE_AMOUNT, TEST_ACCOUNT_ID);
      assertEquals((accountDao.findByAccountID(TEST_ACCOUNT_ID)).getBalance(), 
    		  (UPDATE_AMOUNT + INITIAL_BALANCE), 0);
    }
   
    @AfterMethod
    public void cleanup () {
    	accountManager.deleteAccount (TEST_ACCOUNT_ID);
    }
}
