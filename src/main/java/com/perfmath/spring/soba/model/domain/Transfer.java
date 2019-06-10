package com.perfmath.spring.soba.model.domain;

import java.sql.Timestamp;

import com.perfmath.spring.soba.util.*;
public class Transfer {
    private int transferId;
    private Timestamp transferDate;
    private String fromAccountId;
    private String toAccountId;
    private int fromTxId;
    private int toTxId;
    private double amount;
    private String initiator;
    private String description;
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public Timestamp getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Timestamp transferDate) {
		this.transferDate = transferDate;
	}
	public String getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public String getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	public int getFromTxId() {
		return fromTxId;
	}
	public void setFromTxId(int fromTxId) {
		this.fromTxId = fromTxId;
	}
	public int getToTxId() {
		return toTxId;
	}
	public void setToTxId(int toTxId) {
		this.toTxId = toTxId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}   
}
