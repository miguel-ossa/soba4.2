package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import com.perfmath.spring.soba.model.domain.BankingTx;


public interface AclTxManager extends Serializable{

    public void createTransaction(BankingTx tx);
    public List<BankingTx> getTransactions();
    public void updateTransaction(BankingTx tx);
    public BankingTx findByTransactionID(String txId);
    public void disputeTransaction(String txId);
    public void reverseTransaction(String txId);
    public void deleteTransaction(String txId);
    public List<BankingTx> getTransactions(String accountId);
    public void addPermission(BankingTx tx, Sid recipient, Permission permission);
    
}
