package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Customer implements Serializable {

	private String customerId;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String email;
	private int status;
	private Timestamp createDate;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" customerId: " + customerId + ";");
		buffer.append(" firstName: " + firstName);
		buffer.append(" lastName: " + lastName);
		buffer.append(" phone: " + phone);
		buffer.append(" address: " + address);
		buffer.append(" city: " + city);
		buffer.append(" state: " + state);
		buffer.append(" zipcode: " + zipcode);
		buffer.append(" email: " + email);
		buffer.append(" status: " + status);
		buffer.append(" createDate: " + createDate);
		return buffer.toString();
	}
}