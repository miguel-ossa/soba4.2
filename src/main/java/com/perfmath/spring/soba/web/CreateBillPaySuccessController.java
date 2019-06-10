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
@RequestMapping("/createBillPaySuccess/customerId/{customerId}/accountId/{accountId}")
public class CreateBillPaySuccessController {
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView createBillPaySuccess(@PathVariable("customerId") String customerId,
    		@PathVariable("accountId") String accountId) {
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	myModel.put("customerId", customerId);
    	myModel.put("accountId", accountId);
    	return new ModelAndView("createBillPaySuccess", "model", myModel);
    }
}
