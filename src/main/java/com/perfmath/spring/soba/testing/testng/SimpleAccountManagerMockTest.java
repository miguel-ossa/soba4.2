package com.perfmath.spring.soba.testing.testng;

import static org.easymock.EasyMock.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.perfmath.spring.soba.model.domain.Account;
import com.perfmath.spring.soba.model.dao.AccountDao;
import com.perfmath.spring.soba.service.SimpleAccountManager;

public class SimpleAccountManagerMockTest {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double AMOUNT = 50.0d;
    private AccountDao mock;
    private SimpleAccountManager accountManager;
    private Account account;

    @BeforeMethod
    public void init() {
        account = new Account ();
        account.setAccountId (TEST_ACCOUNT_ID);
        account.setBalance (200.0);
        
        mock = createMock(AccountDao.class);
        accountManager = new SimpleAccountManager ();
        accountManager.setAccountDao (mock);
    }
    
    @Test
    public void createAccount () {
    	mock.insert(account);
    	replay (mock);
    	
        accountManager.createAccount(account);
        verify (mock);
    }

    @Test
    public void updateAccountBalance() {
        expect (mock.findByAccountID(TEST_ACCOUNT_ID)).andReturn (account);
        account.setBalance(AMOUNT + account.getBalance());
        mock.update(account);
        replay(mock);

        accountManager.updateAccountBalance(AMOUNT, TEST_ACCOUNT_ID);
        verify(mock);
    }
}
