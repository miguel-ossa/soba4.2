package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BILL_PAYMENT")
public class BillPayment implements Serializable {
	private Long id = null;
	private String accountId;
	private String description;
	private double amount;
	private String fromAccount;
	private String biller;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String status;
	private Date scheduleDate;
	private Date sendDate;
	public BillPayment() {		
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	//@GeneratedValue (strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "DESCRIPTION", nullable = false, length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "AMOUNT", nullable = false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Column(name = "ACCOUNT_ID", nullable = false, length = 9)
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Column(name = "FROM_ACCOUNT", nullable = false, length = 25)
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	@Column(name = "BILLER", nullable = false, length = 25)
	public String getBiller() {
		return biller;
	}
	public void setBiller(String biller) {
		this.biller = biller;
	}
	@Column(name = "ADDRESS", nullable = false, length = 50)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "CITY", nullable = false, length = 25)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "STATE", nullable = false, length = 2)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "ZIPCODE", nullable = false, length = 10)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name = "STATUS", nullable = false, length = 25)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SCHEDULE_DATE")
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Timestamp scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_DATE")
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" Id: " + id + ";");
		buffer.append(" accountId: " + accountId + ";");
		buffer.append(" description: " + description);
		buffer.append(" amount: " + amount);
		buffer.append(" fromAccount: " + fromAccount);
		buffer.append(" biller: " + biller);
		buffer.append(" address: " + address);
		buffer.append(" city: " + city);
		buffer.append(" state: " + state);
		buffer.append(" zipcode: " + zipcode);
		buffer.append(" status: " + status);
		buffer.append(" scheduleDate: " + scheduleDate);
		buffer.append(" sendDate: " + sendDate);
		return buffer.toString();
	}
}
