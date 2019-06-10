package com.perfmath.spring.soba.testing.spring;

import static org.junit.Assert.*;

import static org.easymock.EasyMock.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.ui.ModelMap;

import com.perfmath.spring.soba.service.AccountManager;
import com.perfmath.spring.soba.web.AccountControllerAnnotated;

public class AccountControllerAnnotatedTest {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double TEST_AMOUNT = 100;
    private static final double NEW_BALANCE = 200;
    private AccountManager mock;
    private AccountControllerAnnotated accountController;

    @BeforeMethod
    public void init() {
    	mock = createMock (AccountManager.class);
        accountController = new AccountControllerAnnotated(mock);
    }

    @Test
    public void updateAccountBalance() {
        expect (mock.updateAccountBalance(TEST_AMOUNT, TEST_ACCOUNT_ID)).andReturn(NEW_BALANCE);
        replay(mock);

        ModelMap model = new ModelMap();
        String viewName =
            accountController.updateAccountBalance (TEST_ACCOUNT_ID, TEST_AMOUNT, model);
        verify(mock);

        assertEquals(viewName, "success");
        assertEquals(model.get("accountId"), TEST_ACCOUNT_ID);
        assertEquals(model.get("balance"), NEW_BALANCE);
    }
}
