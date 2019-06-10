package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import com.perfmath.spring.soba.model.domain.Account;

public interface AccountManager extends Serializable{

    public void createAccount(Account account);
    public Account findByAccountID (String accountId);
    public List<Account> getAccounts();
    public String getAccountIdByCustomerId (String customerId, String accountType);
    public double updateAccountBalance (double amount, String accountId);
    public void deleteAccount(String accountId);  
    public double getBalance(String accountId);
}
