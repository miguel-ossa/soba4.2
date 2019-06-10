package com.perfmath.spring.soba.integration.ejb;

public interface CustomerService {

	public void createCustomer(String name, String password, String email, boolean locked);

}
