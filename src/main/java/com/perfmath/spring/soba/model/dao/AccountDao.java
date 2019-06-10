package com.perfmath.spring.soba.model.dao;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.Account;

public interface AccountDao {
	public List<Account> getAccountList();
    public void insert(Account account);
    public void update(Account account);
   	public double getBalance (String accountID);
   	public double updateAccountBalance (double amount, String accountID);
    public void delete(Account account);
    public Account findByAccountID(String accountID);
    public void insertBatch(List<Account> accounts);
    public List<Map<String, Object>> findAll();
    public String getCustomerId(String accountID);
    public int countAll();
    public String getAccountIdByCustomerId (String customerId, String accountType);
}
