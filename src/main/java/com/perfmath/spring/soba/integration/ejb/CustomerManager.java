package com.perfmath.spring.soba.integration.ejb;

public interface CustomerManager {

	public void createCustomer(String name, String password, String email, boolean locked);
}
