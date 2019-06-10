package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.perfmath.spring.soba.service.AclTxManager;

public class ReverseTxController extends AbstractController {

    private AclTxManager aclTxManager;


    public AclTxManager getAclTxManager() {
		return aclTxManager;
	}


	public void setAclTxManager(AclTxManager aclTxManager) {
		this.aclTxManager = aclTxManager;
	}


	protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String txId =
            ServletRequestUtils.getRequiredStringParameter(request, "txId");
        String accountId = request.getParameter("accountId");
        aclTxManager.reverseTransaction(txId);
        return new ModelAndView("redirect:manageTx.htm?accountId=" + accountId);
    }
}
