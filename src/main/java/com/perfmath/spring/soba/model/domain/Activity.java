package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Activity implements Serializable {

	private String accountId;
	private String name;
	private String accountType;
	private String customerId;
	private String transactionId;
	private String txType;
	private Timestamp transDate;
	private String initiator;
	private String description;
	private double amount;
	private double balance;
	private String status;
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Timestamp getTransDate() {
		return transDate;
	}

	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("customerId:" + customerId + ";");
		buffer.append("accountId: " + accountId + ";");
		buffer.append("account name:" + name + ";");
		buffer.append("account type:" + accountType + ";");
		buffer.append("txId:" + transactionId + ";");
		buffer.append("transDate: " + transDate + ";");
		buffer.append("tx type:" + txType + ";");
		buffer.append("initiator:" + initiator + ";");
		buffer.append("description:" + description + ";");
		buffer.append("amount:" + amount);
		buffer.append("balance:" + balance);
		buffer.append("status:" + status);
		return buffer.toString();
	}
}