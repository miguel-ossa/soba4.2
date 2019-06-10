package com.perfmath.spring.soba.model.dao;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.model.domain.Transfer;

public interface TransferDao {
	public List<Transfer> getTransferList();
    public void insert(Transfer transfer);
    public void update(Transfer transfer);
    public void delete(String transferId);
    public Transfer findByFromTxID(String fromTxId);
    public List<Transfer> findByFromAccountId(String fromAccountId);
    public void insertBatch(List<Transfer> transfers);
    public List<Map<String, Object>> findAll();

    public int countAll();
}
