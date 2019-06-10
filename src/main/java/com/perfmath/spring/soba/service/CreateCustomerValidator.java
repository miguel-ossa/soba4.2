package com.perfmath.spring.soba.service;

import java.sql.Timestamp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

import com.perfmath.spring.soba.model.domain.Customer;
import com.perfmath.spring.soba.util.RandomID;

@Component
public class CreateCustomerValidator implements Validator {

    public boolean supports(Class clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "required.firstName", "firstName is required.");
        ValidationUtils.rejectIfEmpty(errors, "lastName",
                "required.lastName", "lastName is required.");
        ValidationUtils.rejectIfEmpty(errors, "phone",
                "required.phone", "phone is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
                "required.address", "address is required.");
        ValidationUtils.rejectIfEmpty(errors, "city",
                "required.city", "city is required.");
        ValidationUtils.rejectIfEmpty(errors, "state",
                "required.state", "state is required.");

        Customer customer = (Customer) target;
		customer.setCustomerId((new RandomID(9)).getId());
		customer.setStatus(0);
		customer.setCreateDate(new Timestamp(System.currentTimeMillis()));

		String state = customer.getState();

        if (state.length() != 2) {
        	errors.rejectValue("state", "invalid.stateNameLength", "State name must be two letters.");
        }
       

    }
}
