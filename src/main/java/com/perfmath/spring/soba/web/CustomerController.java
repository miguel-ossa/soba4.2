package com.perfmath.spring.soba.web;

import com.perfmath.spring.soba.service.CustomerManager;


import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class CustomerController implements Controller {

    private CustomerManager customerManager;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new java.util.Date()).toString();

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("customers", this.customerManager.getCustomers());
           	
        return new ModelAndView("customerList", "model", myModel);
    }


    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

}