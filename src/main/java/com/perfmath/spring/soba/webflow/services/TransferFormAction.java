package com.perfmath.spring.soba.webflow.services;

import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class TransferFormAction extends FormAction {
public TransferFormAction () {
}
public Event resetForm (RequestContext context) throws Exception{
	return success ();
}
}
