package com.perfmath.spring.soba.web;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.perfmath.spring.soba.service.AclTxManager;
import com.perfmath.spring.soba.service.CreateAclTxValidator;
import com.perfmath.spring.soba.util.RandomID;

@Controller
@RequestMapping("/createAclTxForm/customerId/{customerId}/accountId/{accountId}")
@SessionAttributes("tx")
public class CreateAclTxFormController {

	private CreateAclTxValidator validator;
	private AclTxManager aclTxManager;
	@Autowired
	public CreateAclTxFormController(AclTxManager aclTxManager,
			CreateAclTxValidator validator) {
		this.aclTxManager = aclTxManager;
		this.validator = validator;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable String accountId,
			Model model) {
		BankingTx transaction = new BankingTx();
		transaction.setAccountId(accountId);
		transaction.setType ("acl tx");
		transaction.setInitiator("self");
		transaction.setDescription("acl tx testing");
		model.addAttribute("transaction", transaction);
		return "createAclTxForm";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@PathVariable String customerId,
			@ModelAttribute("transaction") BankingTx transaction,
			BindingResult result, SessionStatus status) {
		validator.validate(transaction, result);
		
        if (result.hasErrors()) {
			return "createAclTxForm";
		} else {
			aclTxManager.createTransaction(transaction);
			status.setComplete();
			return "redirect:/createAclTxSuccess/" + customerId;
		}
	}
	private String getCustomerUsername () {
		String username = "";
		String authority = SecurityContextHolder.getContext()
		.getAuthentication().getAuthorities().toString();
		if (authority.contains ("ROLE_CUST")) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails)principal).getUsername();
			} else {
				username = principal.toString();
			} 
		} else {
			username = "rep";
		}
		return username;
	}
}
