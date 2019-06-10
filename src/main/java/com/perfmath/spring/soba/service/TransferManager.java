package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import com.perfmath.spring.soba.model.domain.Transfer;


public interface TransferManager extends Serializable{

    public void insertTransfer(Transfer transfer);
    public List<Transfer> getTransfers();
    public void updateTransfer(Transfer transferId);
    public void deleteTransfer(String transferId);
    public List<Transfer> findByFromAccountId(String fromAccountId);
}
