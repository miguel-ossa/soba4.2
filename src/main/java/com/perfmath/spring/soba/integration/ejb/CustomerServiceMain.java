package com.perfmath.spring.soba.integration.ejb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.perfmath.spring.soba.util.RandomID;
public class CustomerServiceMain {
	public static void main (String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new
				ClassPathXmlApplicationContext ("beans-customer_0.xml");
		
		CustomerService customerService = 
				context.getBean("customerService", CustomerService.class);
		String customerName = "customer" + (new RandomID(9)).getId();
		System.out.println ("creating " + customerName);
		customerService.createCustomer(customerName, "password", "customer0@abc.com", false);
	}
}
