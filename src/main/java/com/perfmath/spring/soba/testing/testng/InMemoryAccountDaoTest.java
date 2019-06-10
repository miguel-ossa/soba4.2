package com.perfmath.spring.soba.testing.testng;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.testing.InMemoryAccountDao;
import com.perfmath.spring.soba.testing.AccountNotFoundException;
import com.perfmath.spring.soba.testing.DuplicateAccountException;

public class InMemoryAccountDaoTest {

    private static final String EXISTING_ACCOUNT_ID = "88888888";
    private static final String NEW_ACCOUNT_ID = "99999999";
    private static final double EXISTING_ACCOUNT_INITIAL_BALANCE = 800.0;
    private static final double NEW_ACCOUNT_INITIAL_BALANCE = 900.0;
    private static final double UPDATE_AMOUNT = 20.0;
    private static final double UPDATED_BALANCE = 20.0;

    private Account existingAccount;
    private Account newAccount;
    private InMemoryAccountDao accountDao;

    @BeforeMethod
    public void init() {
        existingAccount = new Account();
        existingAccount.setAccountId(EXISTING_ACCOUNT_ID);
        existingAccount.setBalance(EXISTING_ACCOUNT_INITIAL_BALANCE);

        newAccount = new Account();
        newAccount.setAccountId(NEW_ACCOUNT_ID);
        newAccount.setBalance(NEW_ACCOUNT_INITIAL_BALANCE);
        
        accountDao = new InMemoryAccountDao();
        accountDao.insert(existingAccount);
    }
    
    @Test
    public void createNewAccount() {
        accountDao.insert(newAccount);
        assertEquals(accountDao.findByAccountID(NEW_ACCOUNT_ID), newAccount);
    }

    @Test
    public void accountExists() {
        assertTrue(accountDao.accountExists(EXISTING_ACCOUNT_ID));
        assertFalse(accountDao.accountExists(NEW_ACCOUNT_ID));
    }

    @Test(expectedExceptions = DuplicateAccountException.class)
    public void createDuplicateAccount() {
        accountDao.insert(existingAccount);
    }

    @Test
    public void updateExistingAccount() {
        existingAccount.setBalance(UPDATE_AMOUNT);
        accountDao.update(existingAccount);
        assertEquals(accountDao.findByAccountID(EXISTING_ACCOUNT_ID), existingAccount);
    }
    
    @Test
    public void updateAccountBalance() {
    	accountDao.updateAccountBalance(UPDATE_AMOUNT, EXISTING_ACCOUNT_ID);
        assertEquals(accountDao.findByAccountID(EXISTING_ACCOUNT_ID).getBalance(), 
        		(EXISTING_ACCOUNT_INITIAL_BALANCE + UPDATE_AMOUNT), 0);
    }

    @Test(expectedExceptions = AccountNotFoundException.class)
    public void updateNonExistingAccount() {
        accountDao.update(newAccount);
    }

    @Test
    public void removeExistingAccount() {
        accountDao.delete(existingAccount);
        assertFalse(accountDao.accountExists(EXISTING_ACCOUNT_ID));
    }

    @Test(expectedExceptions = AccountNotFoundException.class)
    public void removeNonExistingAccount() {
        accountDao.delete(newAccount);
    }

    @Test
    public void findExistingAccount() {
        Account account = accountDao.findByAccountID(EXISTING_ACCOUNT_ID);
        assertEquals(account, existingAccount);
    }

    @Test(expectedExceptions = AccountNotFoundException.class)
    public void findNonExistingAccount() {
        accountDao.findByAccountID(NEW_ACCOUNT_ID);
    }
}
