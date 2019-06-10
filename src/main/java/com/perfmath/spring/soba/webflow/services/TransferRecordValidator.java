package com.perfmath.spring.soba.webflow.services;

import java.sql.Timestamp;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.apache.commons.lang.StringUtils;

import com.perfmath.spring.soba.model.domain.TransferRecord;
import com.perfmath.spring.soba.util.RandomID;

public class TransferRecordValidator implements Validator {
	public boolean supports(Class clazz) {
		return TransferRecord.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		TransferRecord transferRecord = (TransferRecord) object;

        transferRecord.setId(new Long((new RandomID(10)).getId()));
        transferRecord.setFromTxId(Integer.parseInt(new RandomID(10).getId()));
        transferRecord.setToTxId(Integer.parseInt(new RandomID(10).getId()));
        transferRecord.setInitiator("self");
		transferRecord.setTransferDate(new Timestamp(System.currentTimeMillis()));
		if (StringUtils.isBlank(transferRecord.getDescription())) {
			errors.rejectValue("description", null,
					"Description name must not be empty");
		}

	}
}
