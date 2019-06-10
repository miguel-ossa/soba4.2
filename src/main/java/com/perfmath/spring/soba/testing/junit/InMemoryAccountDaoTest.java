package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.model.dao.AccountDao;
import com.perfmath.spring.soba.testing.InMemoryAccountDao;
import com.perfmath.spring.soba.testing.AccountNotFoundException;
import com.perfmath.spring.soba.testing.DuplicateAccountException;

public class InMemoryAccountDaoTest {

    private static final String EXISTING_ACCOUNT_ID = "88888888";
    private static final String NEW_ACCOUNT_ID = "99999999";

    private Account existingAccount;
    private Account newAccount;
    private InMemoryAccountDao accountDao;

    @Before
    public void init() {
        existingAccount = new Account();
        existingAccount.setAccountId(EXISTING_ACCOUNT_ID);
        existingAccount.setBalance(800.0);

        newAccount = new Account();
        newAccount.setAccountId(NEW_ACCOUNT_ID);
        newAccount.setBalance(900.0);
        
        accountDao = new InMemoryAccountDao();
        accountDao.insert(existingAccount);
    }

    @Test
    public void accountExists() {
        assertTrue(accountDao.accountExists(EXISTING_ACCOUNT_ID));
        assertFalse(accountDao.accountExists(NEW_ACCOUNT_ID));
    }

    @Test
    public void createNewAccount() {
        accountDao.insert(newAccount);
        assertEquals(accountDao.findByAccountID(NEW_ACCOUNT_ID), newAccount);
    }

    @Test(expected = DuplicateAccountException.class)
    public void createDuplicateAccount() {
        accountDao.insert(existingAccount);
    }

    @Test
    public void updateExistingAccount() {
        existingAccount.setBalance(150);
        accountDao.update(existingAccount);
        assertEquals(accountDao.findByAccountID(EXISTING_ACCOUNT_ID), existingAccount);
    }

    @Test(expected = AccountNotFoundException.class)
    public void updateNonExistingAccount() {
        accountDao.update(newAccount);
    }

    @Test
    public void removeExistingAccount() {
        accountDao.delete(existingAccount);
        assertFalse(accountDao.accountExists(EXISTING_ACCOUNT_ID));
    }

    @Test(expected = AccountNotFoundException.class)
    public void removeNonExistingAccount() {
        accountDao.delete(newAccount);
    }

    @Test
    public void findExistingAccount() {
        Account account = accountDao.findByAccountID(EXISTING_ACCOUNT_ID);
        assertEquals(account, existingAccount);
    }

    @Test(expected = AccountNotFoundException.class)
    public void findNonExistingAccount() {
        accountDao.findByAccountID(NEW_ACCOUNT_ID);
    }
}
