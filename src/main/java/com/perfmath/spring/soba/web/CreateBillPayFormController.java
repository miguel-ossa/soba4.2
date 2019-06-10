package com.perfmath.spring.soba.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.perfmath.spring.soba.model.domain.BillPayment;
import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.service.BillPayManager;
import com.perfmath.spring.soba.service.CreateBillPayValidator;

@Controller
@RequestMapping("/createBillPayForm/customerId/{customerId}/accountId/{accountId}")
@SessionAttributes("billPay")
public class CreateBillPayFormController {

	private CreateBillPayValidator validator;
	private BillPayManager billPayManager;
	private AccountManager accountManager;
	@Autowired
	public CreateBillPayFormController(BillPayManager billPayManager,
			AccountManager accountManager, CreateBillPayValidator validator) {
		this.billPayManager = billPayManager;
		this.accountManager = accountManager;
		this.validator = validator;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable String accountId,
			Model model) { 
		BillPayment billPayment = new BillPayment();
		billPayment.setFromAccount(accountId);
		billPayment.setDescription("bill pay test");
		billPayment.setAddress("One Way");
		billPayment.setCity("Any City");
		billPayment.setState("CA");
		billPayment.setZipcode("95999");
		model.addAttribute("billPayment", billPayment);
		return "createBillPayForm";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@PathVariable String customerId,
			@ModelAttribute("billPayment") BillPayment billPayment,
			BindingResult result, SessionStatus status) {
		validator.validate(billPayment, accountManager, result);
		
        if (result.hasErrors()) {
			return "createBillPayForm";
		} else {
			billPayManager.storeBillPayment(billPayment);
			status.setComplete();
			return "redirect:/createBillPaySuccess/customerId/" + customerId
					+ "/accountId/" + billPayment.getFromAccount();
		}
	}
}
