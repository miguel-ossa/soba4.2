package com.perfmath.spring.soba.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.springframework.stereotype.Component;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.util.RandomID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
@Component
public class CreateAccountValidator implements Validator {

    public boolean supports(Class clazz) {
        return Account.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    		UserDetails ud = (UserDetails)principal;
    		String username = ud.getUsername();
    	} else {
    		String username = principal.toString();    	
    	}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "required.name", "name is required.");

        Account account = (Account) target;
        account.setAccountId((new RandomID(9)).getId());
		account.setStatus("0");
		account.setOpenDate(new Timestamp(System.currentTimeMillis()));
      	System.out.println ("account = " + account.toString());
        if ((account.getAccountId()).length() != 9) {
        	errors.reject("invalid.accountIdLength", "accountId must be 9 alphanumeric letters.");
        }
        String cid = account.getCustomerId();
        if (cid.contains(",")) {
       	 int index = cid.lastIndexOf(",");
       	 String newCid = cid.substring(0,index);
       	account.setCustomerId(newCid);
        }

    }
}
