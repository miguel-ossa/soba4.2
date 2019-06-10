package com.perfmath.spring.soba.model.dao;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.Customer;

public interface CustomerDao {
	public List<Customer> getCustomerList();
    public void insert(Customer customer);
    public void update(Customer customer);
    public void delete(Customer customer);
    public Customer findByCustomerID(String customerID);
    public void insertBatch(List<Customer> customers);
    public List<Map<String, Object>> findAll();
    public String getEmail(String customerID);
    public int countAll();
}
