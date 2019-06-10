package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import com.perfmath.spring.soba.model.domain.BankingTx;


public interface TxManager extends Serializable{

    public void createTransaction(BankingTx tx);
    public List<BankingTx> getTransactions();
    public List<BankingTx> getTransactions(String accountId);
    public void updateTransaction(BankingTx tx);
    public BankingTx findByTransactionID(String txId);
    public void deleteTransaction(String txId);
    
}

