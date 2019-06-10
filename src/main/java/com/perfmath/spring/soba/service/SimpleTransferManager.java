package com.perfmath.spring.soba.service;

import java.sql.Timestamp;
import java.util.List;

import com.perfmath.spring.soba.model.dao.BankingTxDao;
import com.perfmath.spring.soba.model.dao.TransferDao;
import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.model.domain.Transfer;
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

public class SimpleTransferManager implements TransferManager {

    private TransferDao transferDao;
    private BankingTxDao bankingTxDao;
    //@Secured({"ROLE_USER", "ROLE_GUEST", "AFTER_ACL_COLLECTION_READ"})
    public List<Transfer> getTransfers() {
        return transferDao.getTransferList();
    }
    public void updateTransfer(Transfer transfer) {
        transferDao.update(transfer);
    }
    @Transactional
   // @Secured("ACL_TRANSACTION_DELETE")
        public void deleteTransfer(String transferId) {
    	transferDao.delete(transferId);
    }
    @Transactional
    //@Secured("ROLE_USER")
    public void insertTransfer(Transfer transfer) {
    	bankingTxDao.insert(getSourceTx (transfer));
    	bankingTxDao.insert(getDestTx (transfer));
    	transferDao.insert(transfer);
    }
    //@Secured({"ROLE_USER", "ROLE_GUEST", "AFTER_ACL_READ"})
    public List<Transfer> findByFromAccountId(String fromAccountId){
        	return transferDao.findByFromAccountId(fromAccountId);
    }

    public void setTransferDao(TransferDao transferDao) {
        this.transferDao = transferDao;
    }
    public void setBankingTxDao(BankingTxDao bankingTxDao) {
        this.bankingTxDao = bankingTxDao;
    }
 
    public BankingTx getSourceTx (Transfer transfer) {
    	BankingTx tx = new BankingTx ();
    	tx.setTransactionId(Integer.parseInt((new RandomID(9)).getId()));
    	tx.setTransDate (new Timestamp(System.currentTimeMillis()));
    	tx.setType ("transfer");
    	tx.setInitiator ("transfer");
    	tx.setDescription("transfer");
    	tx.setAmount (-transfer.getAmount());
    	tx.setBalance (0.0);
    	tx.setStatus("pending");
    	tx.setAccountId (transfer.getFromAccountId());
    	return tx;
    }

    public BankingTx getDestTx (Transfer transfer) {
    	BankingTx tx = new BankingTx ();
    	tx.setTransactionId(Integer.parseInt((new RandomID(9)).getId()));
    	tx.setTransDate (new Timestamp(System.currentTimeMillis()));
    	tx.setType ("transfer");
    	tx.setInitiator ("transfer");
    	tx.setDescription("transfer");
    	tx.setAmount (transfer.getAmount());
    	tx.setBalance (0.0);
    	tx.setStatus("pending");
    	tx.setAccountId (transfer.getToAccountId());
    	return tx;
    }
}
