package com.perfmath.spring.soba.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.model.dao.AccountDao;

public class InMemoryAccountDao implements AccountDao {

    private Map<String, Account> accounts;

    public InMemoryAccountDao() {
        accounts = Collections.synchronizedMap(new HashMap<String, Account>());
    }

    public boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    public void insert(Account account) {
        if (accountExists(account.getAccountId())) {
            throw new DuplicateAccountException();
        }
        accounts.put(account.getAccountId(), account);
    }

    public void update(Account account) {
        if (!accountExists(account.getAccountId())) {
            throw new AccountNotFoundException();
        }
        accounts.put(account.getAccountId(), account);
    }

    public void delete(Account account) {
        if (!accountExists(account.getAccountId())) {
            throw new AccountNotFoundException();
        }
        accounts.remove(account.getAccountId());
    }

    public Account findByAccountID(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }
    
	public List<Account> getAccountList() {
		List<Account> list = (List<Account>) accounts.values();
		return list;
	};

    public double updateAccountBalance (double amount, String accountId) {
    	Account account = findByAccountID(accountId);
    	double balance = amount + account.getBalance();
    	account.setBalance(balance);
    	update (account);
    	return balance;
    }
    public double getBalance (String accountId) {
    	Account account = findByAccountID(accountId);
    	return account.getBalance();
    }
    public void insertBatch(List<Account> accounts) {
    	;
    }
    public List<Map<String, Object>> findAll() {
    	List<Map<String, Object>> list = new ArrayList ();
    	Map <String, Object> objectMap = new HashMap <String, Object>();
    	Set set = (Set) accounts.entrySet();
    	Iterator it = set.iterator ();
    	while (it.hasNext()) {
    		Map.Entry<String, Account> map = (Map.Entry)it.next();
    		objectMap.put (map.getKey().toString(), (Object) map.getValue());
    		list.add(objectMap);
    	}
    	return list;
    }
    public String getCustomerId(String accountID) {
    	return "";
    }
    public int countAll() {
    	return 0;
    }
    public String getAccountIdByCustomerId (String customerId, String accountType) {
    	return "";
    }
}
