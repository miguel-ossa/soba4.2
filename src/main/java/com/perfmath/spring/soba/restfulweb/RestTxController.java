package com.perfmath.spring.soba.restfulweb;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perfmath.spring.soba.model.dao.BankingTxDao;
import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.service.SobaConfig;
import com.perfmath.spring.soba.service.TxManager;

@Controller
@RequestMapping("/restTx")
@SessionAttributes("restTx")
public class RestTxController implements TxManager {
	private AccountManager accountManager;
	private BankingTxDao bankingTxDao;

	private Jaxb2Marshaller jaxb2Mashaller;

	@Autowired
	public RestTxController(BankingTxDao bankingTxDao,
			Jaxb2Marshaller jaxb2Mashaller) {
		super();
		this.bankingTxDao = bankingTxDao;
		this.jaxb2Mashaller = jaxb2Mashaller;
	}

	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}

	private static final String XML_VIEW_NAME = "disputeTx";

	@Secured("ROLE_CUST")
	@RequestMapping(value = "/txId/{transactionId}", method = RequestMethod.GET)
	public ModelAndView getTransactionById(@PathVariable String transactionId) {

		String now = (new java.util.Date()).toString();

		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);

		BankingTx transaction = bankingTxDao.findByTransactionID(transactionId);
		myModel.put("transaction", transaction);
		return new ModelAndView(XML_VIEW_NAME, "model", myModel);
	}

	@Secured("ROLE_CUST")
	@RequestMapping(value = "/txID/{transactionId}", method = RequestMethod.GET)
	public @ResponseBody BankingTx getTransactionByID(@PathVariable String transactionId) {
		BankingTx transaction = bankingTxDao.findByTransactionID(transactionId);
		return transaction;
	}

	@Secured("ROLE_CUST")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createTransaction(@RequestBody String body) {

		Source source = new StreamSource(new StringReader(body));
		BankingTx tx = (BankingTx) jaxb2Mashaller.unmarshal(source);
		if (SobaConfig.getDatabaseVendor().equalsIgnoreCase("SQLServer")) {
			double balance = accountManager.updateAccountBalance(tx.getAmount(), tx.getAccountId());
			tx.setBalance(balance);
		} 

		bankingTxDao.insert(tx); 
		HttpHeaders responseHeaders = new HttpHeaders ();
		responseHeaders.set("method", "createTransaction");
		responseHeaders.set("tx", tx.toString());
		return new ResponseEntity <String> ("Rest createTx succeeded: " + tx.toString(), 
				responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/restTx/txId/{id}")
	public ModelAndView updateTransaction(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		BankingTx tx = (BankingTx) jaxb2Mashaller.unmarshal(source);
		bankingTxDao.update(tx);
		return new ModelAndView(XML_VIEW_NAME, "object", tx);
	}

	public void setbankingTxDao(BankingTxDao bankingTxDao) {
		this.bankingTxDao = bankingTxDao;
	}

	@Transactional
	@Secured("ROLE_CUST, ROLE_REP")
	public void createTransaction(BankingTx tx) {
		bankingTxDao.insert(tx);
	}

	@Transactional
	@Secured("ROLE_REP")
	public void deleteTransaction(String txId) {
		bankingTxDao.delete(txId);
	}

	public BankingTx findByTransactionID(String txId) {
		return bankingTxDao.findByTransactionID(txId);
	}

	public List<BankingTx> getTransactions() {
		return bankingTxDao.getTransactionList();
	}

	public List<BankingTx> getTransactions(String customerId) {
		return bankingTxDao.getTransactionList(customerId);
	}

	public void updateTransaction(BankingTx tx) {
		bankingTxDao.update(tx);
	}
}
