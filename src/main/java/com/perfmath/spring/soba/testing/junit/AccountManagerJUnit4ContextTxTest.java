package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.service.AccountManager;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans-test-tx.xml")
@Transactional
public class AccountManagerJUnit4ContextTxTest {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 500.0;
    private static final double TEST_AMOUNT = 300.0;
    @Autowired
    private AccountManager accountManager;

    @Before
    public void init() {
    }

    @Test
    public void createAccount () {
        Account account = new Account ();
        account.setAccountId(TEST_ACCOUNT_ID);
        account.setCustomerId("585855478");
        account.setName("Testing");
        account.setType("Testing");
        account.setDescription("Spring integration testing");
        account.setBalance(INITIAL_BALANCE);
        account.setStatus("0");
        account.setOpenDate(new Timestamp(System.currentTimeMillis()));
        accountManager.createAccount(account);
        assertEquals(accountManager.getBalance(TEST_ACCOUNT_ID), INITIAL_BALANCE, 0);
    }
    @Test
    public void updateAccountBalance () {
    	createAccount ();
        double balance = accountManager.updateAccountBalance (TEST_AMOUNT, TEST_ACCOUNT_ID);
        assertEquals(accountManager.getBalance(TEST_ACCOUNT_ID), 
      		(INITIAL_BALANCE + TEST_AMOUNT), 0);
    }
}