package com.perfmath.spring.soba.webflow.services;

import java.util.List;

import com.perfmath.spring.soba.model.domain.Customer;
import com.perfmath.spring.soba.webflow.domain.CustomerCriteria;

public interface CustomerService {

    public List<Customer> search(CustomerCriteria criteria);
    public Customer findByCustomerId(String customerId);
}
