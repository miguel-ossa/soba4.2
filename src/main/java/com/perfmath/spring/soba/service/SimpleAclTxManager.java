package com.perfmath.spring.soba.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.transaction.annotation.Transactional;
import com.perfmath.spring.soba.model.dao.AccountDao;
import com.perfmath.spring.soba.model.dao.AclBankingTxDao;
import com.perfmath.spring.soba.model.dao.LoginUserDao;
import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.util.RandomID;
public class SimpleAclTxManager implements AclTxManager {
	private AclBankingTxDao aclBankingTxDao;
	private LoginUserDao loginUserDao;
	private AccountDao accountDao;
	private AccountManager accountManager;
	private MutableAclService mutableAclService;
	public LoginUserDao getLoginUserDao() {
		return loginUserDao;
	}

	public void setLoginUserDao(LoginUserDao loginUserDao) {
		this.loginUserDao = loginUserDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	public MutableAclService getMutableAclService() {
		return mutableAclService;
	}

	public void setMutableAclService(MutableAclService mutableAclService) {
		this.mutableAclService = mutableAclService;
	}
	
	@Secured("AFTER_ACL_READ")
	public BankingTx findByTransactionID(String txId) {
		return aclBankingTxDao.findByTransactionID(txId);
	}

	@Secured("AFTER_ACL_COLLECTION_READ")
	public List<BankingTx> getTransactions() {
		return aclBankingTxDao.getTransactionList();
	}

	public void updateTransaction(BankingTx tx) {
		aclBankingTxDao.update(tx);
	}


	@Transactional
	@Secured({"ROLE_REP", "ACL_TRANSACTION_DELETE", "ACL_TX_DELETE"})

	public void deleteTransaction(String txId) {
 
	aclBankingTxDao.delete(txId);
		ObjectIdentity oid = new ObjectIdentityImpl(BankingTx.class, txId);
		
		mutableAclService.deleteAcl(oid, false);

	}

	public List<BankingTx> getTransactions(String accountId) {
		return aclBankingTxDao.getTransactions(accountId);
	}
private String getCustomerUsername (BankingTx tx) {
	String username = "";
	String authority = SecurityContextHolder.getContext()
	.getAuthentication().getAuthorities().toString();
	if (authority.contains ("ROLE_CUST")) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		} 
	} else {
		String accountId = tx.getAccountId();
		String customerId = accountDao.getCustomerId(accountId);
		username =loginUserDao.getUsernameByCustomerId(customerId);
	}
	return username;
}
	@Transactional
	//@Secured("ROLE_USER")
	public void createTransaction(BankingTx tx) {
		// add due to no insert trigger in sql server
		if (SobaConfig.getDatabaseVendor().equalsIgnoreCase("SQLServer")) {
			//System.out.println ("database vendor is SQL Server");
			double balance = accountManager.updateAccountBalance(tx.getAmount(), tx.getAccountId());
			tx.setBalance(balance);
		} 
		aclBankingTxDao.insert(tx);
		addPermission(tx, new PrincipalSid(getCustomerUsername(tx)), BasePermission.READ);
		addPermission(tx, new GrantedAuthoritySid ("ROLE_REP"), BasePermission.ADMINISTRATION);
		addPermission(tx, new GrantedAuthoritySid ("ROLE_REP"), BasePermission.DELETE);
	}
	@Transactional
	public void disputeTransaction(String txId) {
		BankingTx tx = aclBankingTxDao.findByTransactionID(txId);
		tx.setAmount(0.0);
		tx.setDescription ("Customer disputed (txTd = " + txId + " amount = " + 
				tx.getAmount() + ") for: " + tx.getDescription());
		tx.setStatus ("disputed");
		tx.setTransactionId (Long.parseLong((new RandomID(9)).getId()));
		tx.setTransDate(new Timestamp(System.currentTimeMillis()));
		aclBankingTxDao.insert(tx);
		addPermission(tx, new PrincipalSid(getCustomerUsername(tx)), BasePermission.READ);
		addPermission(tx, new GrantedAuthoritySid ("ROLE_REP"), BasePermission.ADMINISTRATION);
		addPermission(tx, new GrantedAuthoritySid ("ROLE_REP"), BasePermission.DELETE);
	}

	@Transactional
	public void reverseTransaction(String txId) {
		BankingTx tx = aclBankingTxDao.findByTransactionID(txId);
		tx.setAmount(-tx.getAmount());
		tx.setDescription ("Reversed: " + " amount = " + 
				tx.getAmount() + " for " + tx.getDescription());
		tx.setTransactionId (Long.parseLong((new RandomID(9)).getId()));
		tx.setTransDate(new Timestamp(System.currentTimeMillis()));
		aclBankingTxDao.insert(tx);
		addPermission(tx, new PrincipalSid(getCustomerUsername(tx)), BasePermission.READ);
		addPermission(tx, new GrantedAuthoritySid ("ROLE_REP"), BasePermission.ADMINISTRATION);
		addPermission(tx, new GrantedAuthoritySid ("ROLE_REP"), BasePermission.DELETE);
	}

	public void setAclBankingTxDao(AclBankingTxDao aclBankingTxDao) {
		this.aclBankingTxDao = aclBankingTxDao;
	}

    public void addPermission(BankingTx tx, Sid recipient, Permission permission) {
        MutableAcl acl;
        ObjectIdentity oid = new ObjectIdentityImpl(BankingTx.class, tx.getId());

        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
        	System.out.println ("oid=" + oid.toString());
            acl = mutableAclService.createAcl(oid);
       }

        acl.insertAce(acl.getEntries().size(), permission, recipient, true);
        mutableAclService.updateAcl(acl);

    }
    public void deletePermission(BankingTx tx, Sid recipient, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(BankingTx.class, tx.getTransactionId());
        MutableAcl acl = (MutableAcl) mutableAclService.readAclById(oid);

        // Remove all permissions associated with this particular recipient (string equality to KISS)
        List<AccessControlEntry> entries = acl.getEntries();

        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getSid().equals(recipient) && entries.get(i).getPermission().equals(permission)) {
                acl.deleteAce(i);
            }
        }

        mutableAclService.updateAcl(acl);
    }

}
