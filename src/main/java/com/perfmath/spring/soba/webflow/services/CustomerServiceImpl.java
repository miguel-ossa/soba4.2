package com.perfmath.spring.soba.webflow.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.perfmath.spring.soba.model.domain.Customer;
import com.perfmath.spring.soba.webflow.domain.CustomerCriteria;
import com.perfmath.spring.soba.model.dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public List<Customer> search(CustomerCriteria criteria) {
        String zipcode = criteria.getZipcode().trim();
        String lastName = criteria.getLastName().trim();
        
        List<Customer> customers = customerDao.getCustomerList ();
        
        List<Customer> results = new ArrayList<Customer>();
        Iterator it = customers.iterator();
        
        while (it.hasNext()) {
        	Customer customer = (Customer) it.next();
            boolean zipcodeMatches = zipcode.length() > 0
                    && customer.getZipcode().equals(zipcode);
            boolean lastNameMatches = customer.getLastName().equals(lastName);
            if (zipcodeMatches || lastNameMatches) {
                results.add(customer);
            }
        }
        return results;
    }

    public Customer findByCustomerId(String customerId) {
    	Customer customer = customerDao.findByCustomerID(customerId);
        return customer;
    }
}
