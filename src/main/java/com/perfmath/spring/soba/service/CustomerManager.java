package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import com.perfmath.spring.soba.model.domain.Customer;


public interface CustomerManager extends Serializable{

    public void createCustomer(Customer customer);
    
    public List<Customer> getCustomers();
    
}
