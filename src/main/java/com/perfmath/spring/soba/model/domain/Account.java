package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Account implements Serializable {

    private String accountId;
    private String name;
    private String type;
    private String description;
    private String status;
    private double balance;
    private Timestamp openDate;
    private Timestamp closeDate;
    private String customerId;
  
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Timestamp getOpenDate() {
		return openDate;
	}


	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public Timestamp getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }
        Account account = (Account) obj;
        return account.accountId.equals(accountId) && account.balance == balance;
    }
    
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("accountId: " + accountId + ";");
        buffer.append("customerId:" + customerId + ";");
        buffer.append("balance:" + balance);
        buffer.append("name: " + name + ";");
        buffer.append("type:" + type + ";");
        buffer.append("description:" + description);
        buffer.append("openDate:" + openDate + ";");
        buffer.append("closeDate:" + closeDate);
        buffer.append("status:" + status);
        return buffer.toString();
    }
}