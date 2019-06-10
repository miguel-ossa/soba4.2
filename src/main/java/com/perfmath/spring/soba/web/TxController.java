package com.perfmath.spring.soba.web;

import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.service.TxManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
//@RequestMapping("/tx/{transactionId}")
@SessionAttributes("tx")
public class TxController implements Controller {

    private TxManager txManager;
	@Autowired
	public TxController(TxManager txManager) {
		this.txManager = txManager;
	}
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new java.util.Date()).toString();

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        //myModel.put("tx", now);
        String txId = request.getParameter("txId");
        String accountId = request.getParameter("accountId");
        String customerId = request.getParameter("customerId");
        myModel.put("customerId", customerId);
        myModel.put("accountId", accountId);
        if (txId != null) {
        	System.out.println ("Non - Rest Test (id = ): " + txId);
    		BankingTx tx = txManager.findByTransactionID(txId);
            myModel.put("transaction", tx);
            return new ModelAndView("disputeTx", "model", myModel);
           // return new ModelAndView("restTxList", "transaction", tx);
        } else {
        	System.out.println ("Non - Rest Test (accountId = ): " + accountId);
        	myModel.put("transactions", txManager.getTransactions(accountId));
            return new ModelAndView("transactionList", "model", myModel);
        }

    }


    public void setTxManager(TxManager txManager) {
        this.txManager = txManager;
    }
}