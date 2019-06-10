package com.perfmath.spring.soba.service;

import java.sql.Timestamp;
import java.util.List;

import com.perfmath.spring.soba.model.dao.BankingTxDao;
import com.perfmath.spring.soba.model.dao.BillPaymentDao;
import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.model.domain.BillPayment;
import com.perfmath.spring.soba.service.AclTxManager;
import com.perfmath.spring.soba.util.RandomID;
/*
 import org.springframework.security.acls.MutableAcl;
 import org.springframework.security.acls.MutableAclService;
 import org.springframework.security.acls.domain.BasePermission;
 import org.springframework.security.acls.objectidentity.ObjectIdentity;
 import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
 import org.springframework.security.acls.sid.GrantedAuthoritySid;
 import org.springframework.security.acls.sid.PrincipalSid;
 import org.springframework.security.annotation.Secured;
 */
import org.springframework.transaction.annotation.Transactional;

public class SimpleBillPayManager implements BillPayManager {

	private BillPaymentDao billPaymentDao;
	private AclTxManager aclTxManager;

	// @Secured({"ROLE_USER", "ROLE_GUEST", "AFTER_ACL_COLLECTION_READ"})
	public List<BillPayment> getBillPayments() {
		return billPaymentDao.findAll();
	}

	public void storeBillPayment(BillPayment billPayment) {
		billPaymentDao.store(billPayment);
		aclTxManager.createTransaction(debitTransaction(billPayment));
		aclTxManager.createTransaction(creditTransaction(billPayment));
	}

	@Transactional
	// @Secured("ACL_TRANSACTION_DELETE")
	public void deleteBillPayment(String id) {
		billPaymentDao.delete(id);
	}

	@Transactional
	// @Secured("ROLE_USER")
	/*
	 * public void insertBillPayment(BillPayment billPayment) {
	 * billPaymentDao.insert(billPayment); }
	 */
	// @Secured({"ROLE_USER", "ROLE_GUEST", "AFTER_ACL_READ"})
	public BillPayment findById(String id) {
		return billPaymentDao.findById(id);
	}

	public void setBillPaymentDao(BillPaymentDao billPaymentDao) {
		this.billPaymentDao = billPaymentDao;
	}
	public AclTxManager getAclTxManager() {
		return aclTxManager;
	}

	public void setAclTxManager(AclTxManager aclTxManager) {
		this.aclTxManager = aclTxManager;
	}

	private BankingTx debitTransaction(BillPayment billPayment) {
		BankingTx transaction = new BankingTx();
		transaction.setTransactionId(Long.parseLong((new RandomID(9)).getId()));
		transaction.setTransDate(new Timestamp(System.currentTimeMillis()));
		transaction.setStatus("complete");
		transaction.setAccountId(billPayment.getFromAccount());
		transaction.setAmount(-billPayment.getAmount());
		transaction.setType("bill pay");
		transaction.setInitiator("online bill payment");
		transaction.setDescription("pay to " + billPayment.getBiller());
		transaction.setBalance(0);
		return transaction;
	}
	private BankingTx creditTransaction(BillPayment billPayment) {
		BankingTx transaction = new BankingTx();
		transaction.setTransactionId(Long.parseLong((new RandomID(9)).getId()));
		transaction.setTransDate(new Timestamp(System.currentTimeMillis()));
		transaction.setStatus("pending");
		transaction.setAccountId(billPayment.getAccountId());
		transaction.setAmount(billPayment.getAmount());
		transaction.setType("bill pay");
		transaction.setInitiator("online bill payment");
		transaction.setDescription("received from account " + billPayment.getFromAccount());
		transaction.setBalance(0);
		return transaction;
	}
}
