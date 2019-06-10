package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TRANSFER")
public class TransferRecord implements Serializable {
    private Long id=null;
    private Date transferDate;
    private String fromAccountId;
    private String toAccountId;
    private int fromTxId;
    private int toTxId;
    private double amount;
    private String initiator;
    private String description;
    public TransferRecord () {
    }
    @Id
	@Column(name = "TRANSFER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFER_DATE")
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	@Column(name = "FROM_ACCOUNT_ID", nullable = false, length = 10)
	public String getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	@Column(name = "TO_ACCOUNT_ID", nullable = false, length = 10)
	public String getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	@Column(name = "FROM_TX_ID", nullable = false, length = 10)
	public int getFromTxId() {
		return fromTxId;
	}
	public void setFromTxId(int fromTxId) {
		this.fromTxId = fromTxId;
	}
	@Column(name = "TO_TX_ID", nullable = false, length = 10)
	public int getToTxId() {
		return toTxId;
	}
	public void setToTxId(int toTxId) {
		this.toTxId = toTxId;
	}
	@Column(name = "AMOUNT", nullable = false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Column(name = "INITIATOR", nullable = false, length = 10)
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	@Column(name = "DESCRIPTION", nullable = false, length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}   
}
