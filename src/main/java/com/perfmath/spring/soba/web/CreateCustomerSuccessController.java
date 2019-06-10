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
public class CreateCustomerSuccessController {
    @RequestMapping(value="/createCustomerSuccess/customerId/{customerId}", method=RequestMethod.GET)
    public ModelAndView createCustomerSuccess(@PathVariable("customerId") String customerId) {
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	myModel.put("customerId", customerId);
    	return new ModelAndView("createCustomerSuccess", "model", myModel);
    }
}
