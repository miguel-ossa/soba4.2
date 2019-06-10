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

import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.model.domain.Customer;
import com.perfmath.spring.soba.util.RandomID;

@Component
public class CreateTxValidator implements Validator {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	public boolean supports(Class clazz) {
		return BankingTx.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		BankingTx transaction = (BankingTx) target;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails ud = (UserDetails) principal;
			String username = ud.getUsername();
		}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
					"required.amount", "amount is required.");
			transaction.setTransactionId(Long.parseLong((new RandomID(10)).getId()));
			transaction.setTransDate(new Timestamp(System.currentTimeMillis()));
			transaction.setStatus("complete");
	}
}