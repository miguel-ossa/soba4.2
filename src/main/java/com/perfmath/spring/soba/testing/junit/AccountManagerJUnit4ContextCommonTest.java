package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.assertEquals;
import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.perfmath.spring.soba.service.AccountManager;

@ContextConfiguration(locations = "/beans-test-tx.xml")
public class AccountManagerJUnit4ContextCommonTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 1500.0;
    private static final double TEST_AMOUNT = 300.0;

    @Autowired
    private AccountManager accountManager;

    @Before
    public void init() {
        jdbcTemplate.update( "INSERT INTO ACCOUNT (ACCOUNT_ID, NAME, TYPE, DESCRIPTION, STATUS, "
				+ " BALANCE, OPEN_DATE, CLOSE_DATE, CUSTOMER_ID) "
				+ "VALUES (?, ?, ?,?, ?, ?, ?, ?, ?)", TEST_ACCOUNT_ID, 
				"Testing", "Testing", "Spring integration testing",  "0", INITIAL_BALANCE,
				new Timestamp(System.currentTimeMillis()), null, "585855478");
		jdbcTemplate.update("UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?", 
				(INITIAL_BALANCE + TEST_AMOUNT), TEST_ACCOUNT_ID);
    }

    @Test
    @Timed(millis = 900)
    public void deposit() {
        accountManager.updateAccountBalance (TEST_AMOUNT, TEST_ACCOUNT_ID);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?",
                Double.class, TEST_ACCOUNT_ID);
        assertEquals(balance, (INITIAL_BALANCE + 2*TEST_AMOUNT), 0);
    }

    @Test
    @Repeat(3)
    public void withDraw() {
        accountManager.updateAccountBalance (-TEST_AMOUNT, TEST_ACCOUNT_ID);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?",
                Double.class, TEST_ACCOUNT_ID);
        assertEquals(balance, INITIAL_BALANCE, 0);
    }
}
