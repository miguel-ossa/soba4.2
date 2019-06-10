package com.perfmath.spring.soba.web;

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
import org.springframework.web.servlet.ModelAndView;

import com.perfmath.spring.soba.model.domain.Transfer;
import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.service.TransferManager;
import com.perfmath.spring.soba.service.TransferValidator;

@Controller
@RequestMapping("/transferForm/customerId/{customerId}")
@SessionAttributes("transfer")
public class TransferFormController {

	private TransferValidator validator;
	private TransferManager transferManager;
	private AccountManager accountManager;
	@Autowired
	public TransferFormController(TransferManager transferManager,
			TransferValidator validator, AccountManager accountManager) {
		this.transferManager = transferManager;
		this.validator = validator;
		this.accountManager = accountManager;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable String customerId,
			Model model) {
		Transfer transfer = new Transfer();
		transfer.setDescription("transfer");
		model.addAttribute("transfer", transfer);
		model.addAttribute("customerId", customerId);
		return "transferForm";	
	}
	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@PathVariable String customerId,
			@ModelAttribute("transfer") Transfer transfer,
			BindingResult result, SessionStatus status) {
			// parse from and to accounts
		
		String checkingAccountId = accountManager.getAccountIdByCustomerId(customerId, "Checking");
		String savingsAccountId = accountManager.getAccountIdByCustomerId(customerId, "Savings");
		if (transfer.getFromAccountId().contains ("Checking")) {
			transfer.setFromAccountId(checkingAccountId);
		} else if (transfer.getFromAccountId().contains ("Savings")){
			transfer.setFromAccountId(savingsAccountId);
		}
		if (transfer.getToAccountId().contains ("Checking")) {
			transfer.setToAccountId(checkingAccountId);
		} else if (transfer.getToAccountId().contains ("Savings")){
			transfer.setToAccountId(savingsAccountId);
		}
		System.out.println("submit transferForm..." + transfer.getFromAccountId());
		validator.validate(transfer, result);
		
        if (result.hasErrors()) {
			return "transferForm";
		} else {
			transferManager.insertTransfer(transfer);
			status.setComplete();
			return "redirect:/transferSuccess/customerId/" + customerId;
		}
	}
}
