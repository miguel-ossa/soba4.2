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
import org.springframework.web.servlet.ModelAndView;

import com.perfmath.spring.soba.model.domain.LoginUser;
import com.perfmath.spring.soba.service.CreateLoginUserValidator;
import com.perfmath.spring.soba.service.LoginUserManager;
import com.perfmath.spring.soba.util.RandomID;

@Controller
@RequestMapping("/createLoginUserForm/customerId/{customerId}")
@SessionAttributes("loginUser")
public class CreateLoginUserFormController {
	private CreateLoginUserValidator validator;
	private LoginUserManager loginUserManager;
	@Autowired
	public CreateLoginUserFormController(LoginUserManager loginUserManager,
			CreateLoginUserValidator validator) {
		this.loginUserManager = loginUserManager;
		this.validator = validator;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(
			@PathVariable String customerId,
			Model model) {
		LoginUser loginUser = new LoginUser();
		loginUser.setCustomerId(customerId);
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("username", "loginUser");

		return "createLoginUserForm";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(
			@ModelAttribute("loginUser") LoginUser loginUser,
			BindingResult result, SessionStatus status) {
		validator.validate(loginUser, result);
		
        if (result.hasErrors()) {
			return "createLoginUserForm";
		} else {
			loginUserManager.createLoginUser(loginUser);
			status.setComplete();
			return "redirect:/createLoginUserSuccess/customerId/" + loginUser.getCustomerId()
			+ "/username/" + loginUser.getUsername();
		}
	}
}
