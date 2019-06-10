package com.perfmath.spring.soba.service;

import java.sql.Timestamp;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.perfmath.spring.soba.model.domain.Transfer;
import com.perfmath.spring.soba.util.RandomID;
@Component
public class TransferValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return Transfer.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	Transfer transfer = (Transfer) target;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    		UserDetails ud = (UserDetails)principal;
    		String username = ud.getUsername();
    	} else {
    		String username = principal.toString();    	
    		System.out.println ("current user details:" + username);
    	}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
                "required.amount", "amount is required.");
        transfer.setTransferId(Integer.parseInt((new RandomID(10)).getId()));
        transfer.setFromTxId(Integer.parseInt((new RandomID(10)).getId()));
        transfer.setInitiator("test");
        transfer.setToTxId(Integer.parseInt((new RandomID(10)).getId()));
        transfer.setTransferDate(new Timestamp(System.currentTimeMillis()));
    }
}
