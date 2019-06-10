package com.perfmath.spring.soba.model.dao;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.BankingTx;

public interface AclBankingTxDao {
	public List<BankingTx> getTransactionList();
    public void insert(BankingTx transaction);
    public void update(BankingTx transaction);
    public void delete(String transactionId);
    public BankingTx findByTransactionID(String transactionId);
    public void insertBatch(List<BankingTx> transactions);
    public List<Map<String, Object>> findAll();
    public String getAccountId(String transactionId);
    public int countAll();
    public List<BankingTx> getTransactions(String accountId);
}
