package com.perfmath.spring.soba.web;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.service.CreateTxValidator;
import com.perfmath.spring.soba.service.TxManager;
import com.perfmath.spring.soba.util.RandomID;

@Controller
@RequestMapping("/createTxForm/customerId/{customerId}/accountId/{accountId}")
@SessionAttributes("tx")
public class CreateTxFormController {

	private CreateTxValidator validator;
	private TxManager txManager;
	@Autowired
	public CreateTxFormController(TxManager txManager,
			CreateTxValidator validator) {
		this.txManager = txManager;
		this.validator = validator;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable String accountId,
			Model model) { 
		BankingTx transaction = new BankingTx();
		transaction.setAccountId(accountId);
		transaction.setType ("regular");
		transaction.setInitiator("self");
		transaction.setDescription("testing");
		model.addAttribute("transaction", transaction);
		return "createTxForm";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@PathVariable String customerId,
			@ModelAttribute("transaction") BankingTx transaction,
			BindingResult result, SessionStatus status) {
		validator.validate(transaction, result);
		
        if (result.hasErrors()) {
			return "createTxForm";
		} else {
			txManager.createTransaction(transaction);
			status.setComplete();
			return "redirect:/createTxSuccess/" + customerId;
		}
	}
}
