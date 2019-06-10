package com.perfmath.spring.soba.service;

import java.util.List;

import com.perfmath.spring.soba.model.dao.AccountDao;
import com.perfmath.spring.soba.model.domain.Account;

public class SimpleAccountManager implements AccountManager {

    private AccountDao accountDao;

    public List<Account> getAccounts() {
        return accountDao.getAccountList();
    }

    public void createAccount(Account account) {
    	accountDao.insert(account);
    }
    public void updateAccount(Account account) {
    	accountDao.update(account);
    }
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    public Account findByAccountID (String accountId) {
    	return accountDao.findByAccountID(accountId);
    }
    public String getAccountIdByCustomerId (String customerId, String accountType) {
		return accountDao.getAccountIdByCustomerId(customerId, accountType);
    }
    
    public double updateAccountBalance (double amount, String accountId) {
    	/*
    	Account account = accountDao.findByAccountID(accountId);
    	double balance = amount + account.getBalance();
    	account.setBalance(balance);
    	accountDao.update(account);
    	*/
    	return accountDao.updateAccountBalance(amount, accountId);
    	//return balance;
    }

    public void deleteAccount(String accountId) {
    	Account account = accountDao.findByAccountID(accountId);
    	accountDao.delete(account);
    }
    public double getBalance(String accountId) {
        return accountDao.getBalance(accountId);
    }
}
