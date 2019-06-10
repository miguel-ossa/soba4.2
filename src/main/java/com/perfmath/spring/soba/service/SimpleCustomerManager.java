package com.perfmath.spring.soba.service;

import java.util.List;

import com.perfmath.spring.soba.model.dao.CustomerDao;
import com.perfmath.spring.soba.model.domain.Customer;

public class SimpleCustomerManager implements CustomerManager {

    private CustomerDao customerDao;

    public List<Customer> getCustomers() {
        return customerDao.getCustomerList();
    }

    public void createCustomer(Customer customer) {
    	customerDao.insert(customer);
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
