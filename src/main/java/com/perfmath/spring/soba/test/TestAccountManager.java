package com.perfmath.spring.soba.test;

import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.service.AccountManager;

public class TestAccountManager {
    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 500.0;
    private static final double TEST_AMOUNT = 300.0;
	public static void main (String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext ("/beans-test-tx.xml");
		AccountManager accountManager = context.getBean("accountManager", AccountManager.class);
		System.out.println ("creating account 88889999");
		createAccount (accountManager);
		double balance = updateAccountBalance (accountManager);
        System.out.println ("account balance = " + balance);
	}
    public static void createAccount (AccountManager accountManager) {
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
    }

    public static double updateAccountBalance (AccountManager accountManager) {
        double balance = accountManager.updateAccountBalance (TEST_AMOUNT, TEST_ACCOUNT_ID);
        return balance;
    }
}
