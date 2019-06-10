package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
//import org.springframework.context.
import com.perfmath.spring.soba.service.AclTxManager;

public class ManageTxController extends AbstractController {
    private AclTxManager aclTxManager;


    public AclTxManager getAclTxManager() {
		return aclTxManager;
	}


	public void setAclTxManager(AclTxManager aclTxManager) {
		this.aclTxManager = aclTxManager;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String customerId = request.getParameter("customerId");
		
		String accountId = request.getParameter("accountId");
		return new ModelAndView("manageTx",
                "txs", aclTxManager.getTransactions(accountId));
    }
}
