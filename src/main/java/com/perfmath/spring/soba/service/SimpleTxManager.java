package com.perfmath.spring.soba.service;

import java.util.List;

import com.perfmath.spring.soba.model.dao.BankingTxDao;
import com.perfmath.spring.soba.model.domain.BankingTx;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.annotation.Secured;

public class SimpleTxManager implements TxManager {

	private BankingTxDao bankingTxDao;
	private AccountManager accountManager;

	// @Secured({"ROLE_USER", "ROLE_GUEST", "AFTER_ACL_COLLECTION_READ"})
	public List<BankingTx> getTransactions() {
		return bankingTxDao.getTransactionList();
	}
	public List<BankingTx> getTransactions(String accountId) {
		return bankingTxDao.getTransactionList(accountId);
	}

	public void updateTransaction(BankingTx tx) {
		bankingTxDao.update(tx);
	}

	// @Secured("ROLE_REP")
	@Transactional
	@Secured("ROLE_REP")
	public void deleteTransaction(String txId) {
		bankingTxDao.delete(txId);
	}

	@Transactional
	@Secured("ROLE_CUST, ROLE_REP")
	public void createTransaction(BankingTx tx) {
		// add due to no insert trigger in sql server
		if (SobaConfig.getDatabaseVendor().equalsIgnoreCase("SQLServer")) {
			//System.out.println ("database vendor is SQL Server");
			double balance = accountManager.updateAccountBalance(tx.getAmount(), tx.getAccountId());
			tx.setBalance(balance);
		} 
		bankingTxDao.insert(tx);
	}

	// @Secured({"ROLE_USER", "ROLE_GUEST", "AFTER_ACL_READ"})

	public BankingTx findByTransactionID(String txId) {
		return bankingTxDao.findByTransactionID(txId);
	}

	public void setBankingTxDao(BankingTxDao bankingTxDao) {
		this.bankingTxDao = bankingTxDao;
	}
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
