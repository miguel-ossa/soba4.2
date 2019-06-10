package com.perfmath.spring.soba.integration.ejb;

public class CustomerServiceImpl implements CustomerService {
	private CustomerManager customerManager;

	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	public void createCustomer(String name, String password, String email,
			boolean locked) {
		customerManager.createCustomer(name, password, email, locked);
	}

}
