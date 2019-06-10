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

import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.model.domain.BillPayment;
import com.perfmath.spring.soba.util.RandomID;

@Component
public class CreateBillPayValidator implements Validator {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	public boolean supports(Class clazz) {
		return BillPayment.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		BillPayment billPayment = (BillPayment) target;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails ud = (UserDetails) principal;
			String username = ud.getUsername();
		}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
					"required.amount", "amount is required.");
			if (billPayment.getAmount() <= 0.0) {
				errors.rejectValue("amount", "invalid.billPayAmount", "bill pay amount must be > 0");
			}
			if (billPayment.getAccountId().isEmpty()) {
				errors.rejectValue("accountId", "invalid.recieverAcountId", "Reciever accountId must exist");
			}
			billPayment.setId(Long.parseLong(new RandomID(10).getId()));
			billPayment.setScheduleDate(new Timestamp(System.currentTimeMillis()));
			billPayment.setSendDate(new Timestamp(System.currentTimeMillis()));
			billPayment.setStatus("complete");
	}
	public void validate(Object target, AccountManager accountManager, Errors errors) {
		validate (target, errors);
		BillPayment billPayment = (BillPayment) target;
		String revieverAccountId = billPayment.getAccountId();
		Account revieverAccount = accountManager.findByAccountID (revieverAccountId);
		if (revieverAccount == null) {
			errors.rejectValue("accountId", "invalid.recieverAcountId", "Reciever accountId must exist");
		}
	}
}