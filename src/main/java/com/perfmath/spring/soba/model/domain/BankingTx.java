package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.perfmath.spring.soba.model.domain.TimestampAdapter;
@XmlRootElement(name="bankingtx")
public class BankingTx implements Serializable {

    private long transactionId;
    private Timestamp transDate;
    private String type;
    private String initiator;
    private String description;
    private double amount;
    private double balance;
    private String accountId;
    private String status;
    
	@XmlElement(name="transactionId")
	public long getTransactionId() {
		return transactionId;
	}
	public long getId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	//@XmlElement(name="transDate")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getTransDate() {
		return transDate;
	}


	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}

	@XmlElement(name="type")
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name="initiator")
	public String getInitiator() {
		return initiator;
	}


	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name="amount")
	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	@XmlElement(name="balance")
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

	@XmlElement(name="accountId")
	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("transactionId: " + transactionId + ";");
        buffer.append("accountId: " + accountId + ";");
        buffer.append("amount: " + amount);
        return buffer.toString();
    }
	
}