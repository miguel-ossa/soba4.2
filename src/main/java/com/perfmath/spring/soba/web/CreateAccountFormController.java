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

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.service.CreateAccountValidator;
import com.perfmath.spring.soba.util.RandomID;

@Controller
@RequestMapping("/createAccountForm/customerId/{customerId}")
@SessionAttributes("account")
public class CreateAccountFormController {

	private CreateAccountValidator validator;
	private AccountManager accountManager;
	@Autowired
	public CreateAccountFormController(AccountManager accountManager,
			CreateAccountValidator validator) {
		this.accountManager = accountManager;
		this.validator = validator;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable String customerId,
			Model model) {
		Account account = new Account();
		account.setCustomerId(customerId);
		account.setName("checking");
		account.setDescription("my checking online");
		model.addAttribute("account", account);
		return "createAccountForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@ModelAttribute("account") Account account,
			BindingResult result, SessionStatus status) {
		System.out.println ("createAccountForm:1");
		validator.validate(account, result);
		
        if (result.hasErrors()) {
        	System.out.println ("createAccountForm:2 " + result.toString());
			return "createAccountForm";
		} else {
			System.out.println ("createAccountForm:create ...");
			accountManager.createAccount(account);
			status.setComplete();
			System.out.println ("createAccountForm:redirect ..." + account.getCustomerId());
			return "redirect:/createAccountSuccess/customerId/" + account.getCustomerId();
		}
	}
}
