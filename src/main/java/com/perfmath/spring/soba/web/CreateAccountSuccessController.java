package com.perfmath.spring.soba.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CreateAccountSuccessController {
    @RequestMapping(value="/createAccountSuccess/customerId/{customerId}", method=RequestMethod.GET)
    public ModelAndView createAccountSuccess(@PathVariable("customerId") String customerId) {
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	myModel.put("customerId", customerId);
    	System.out.println ("createAcoountSuccess: ");
    	return new ModelAndView("createAccountSuccess", "model", myModel);
    }
}
