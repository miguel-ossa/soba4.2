package com.perfmath.spring.soba.service;

import java.sql.Timestamp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.Component;

import com.perfmath.spring.soba.model.domain.LoginUser;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
@Component
public class CreateLoginUserValidator implements Validator {

    public boolean supports(Class clazz) {
        return LoginUser.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	   LoginUser user = (LoginUser) target;
    	   System.out.println ("validate 1: customerId: " + user.getCustomerId());
    	   /*
    	   StandardPasswordEncoder encoder = new StandardPasswordEncoder ();
    	 	String encyptedPassword = encoder.encode (user.getPassword ());
    	 	user.setPassword(encyptedPassword);
    	    */
    	if (principal instanceof UserDetails) {
    		UserDetails ud = (UserDetails)principal;
    		String username = ud.getUsername();
        	System.out.println ("current user name:" + username);
        	if (ud.isEnabled()){
        	System.out.println (" {current user is enabled:");
        	} else {
        		System.out.println (" {current user is not enabled:");
        	}
    	} else {
    		String username = principal.toString();    	
    		System.out.println ("current user details:" + username);
    	}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "required.username", "username is required.");
        ValidationUtils.rejectIfEmpty(errors, "password",
                "required.password", "password is required.");
        ValidationUtils.rejectIfEmpty(errors, "enabled",
                "required.enabled", "enabled is required.");
        ValidationUtils.rejectIfEmpty(errors, "customerId",
                "required.customerId", "customerIdd is required.");
     
        user.setCreateDate(new Timestamp(System.currentTimeMillis()));

        if (user.getUsername().length() < 5) {
        	errors.reject("invalid.username", "minimum username length is five.");
        }
     String cid = user.getCustomerId();
     // set enabled true
     user.setEnabled(1);
     if (cid.contains(",")) {
    	 int index = cid.lastIndexOf(",");
    	 String newCid = cid.substring(0,index);
    	 user.setCustomerId(newCid);
     }
 	   System.out.println ("validate 2: customerId: " + user.getCustomerId());
    }
}
