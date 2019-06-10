package com.perfmath.spring.soba.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.perfmath.spring.soba.model.domain.Customer;
import com.perfmath.spring.soba.service.CreateCustomerValidator;
import com.perfmath.spring.soba.service.CustomerManager;

@Controller
@RequestMapping("/createCustomerForm")
@SessionAttributes("customer")
public class CreateCustomerFormController {
	private CreateCustomerValidator validator;
	private CustomerManager customerManager;
	@Autowired
	public CreateCustomerFormController(CustomerManager customerManager,
			CreateCustomerValidator validator) {
		this.customerManager = customerManager;
		this.validator = validator;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@RequestParam(required = false, value = "username") String username,
			Model model) {
		Customer customer = new Customer();
		customer.setFirstName("user");
		customer.setLastName("soba");
		customer.setPhone ("999-999-9999");
		customer.setAddress("One Way");
		customer.setCity("Any City");
		customer.setState("CA");
		customer.setZipcode("95999");
		customer.setEmail("user@soba.com");
		model.addAttribute("customer", customer);
		return "createCustomerForm";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@ModelAttribute("customer") Customer customer,
			BindingResult result, SessionStatus status) {
		validator.validate(customer, result);
		
        if (result.hasErrors()) {
			return "createCustomerForm";
		} else {
			customerManager.createCustomer(customer);
			status.setComplete();
			return "redirect:createCustomerSuccess/customerId/" + customer.getCustomerId();
		}
	}
}
