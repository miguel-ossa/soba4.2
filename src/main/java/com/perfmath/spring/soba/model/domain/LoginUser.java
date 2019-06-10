package com.perfmath.spring.soba.model.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoginUser implements Serializable {

    private String username;
    private String password;
    private int enabled;
    private Timestamp createDate;
    private Timestamp closeDate;
    private String customerId;
    
   
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getEnabled() {
		return enabled;
	}


	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


	public Timestamp getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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


	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("username: " + username + ";");
        buffer.append("customerId:" + customerId + ";");
        buffer.append("enabled:" + enabled);
        return buffer.toString();
    }
}