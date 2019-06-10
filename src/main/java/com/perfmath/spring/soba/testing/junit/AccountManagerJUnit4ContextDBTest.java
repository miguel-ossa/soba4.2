package com.perfmath.spring.soba.testing.junit;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.perfmath.spring.soba.service.AccountManager;

@ContextConfiguration(locations = "/beans-test-tx.xml")
public class AccountManagerJUnit4ContextDBTest extends
        AbstractTransactionalJUnit4SpringContextTests {

	private static final String TEST_ACCOUNT_ID = "88889999";
    private static final double INITIAL_BALANCE = 500.0;
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
		String sql1 = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
		int count = this.jdbcTemplate.update(sql1, (INITIAL_BALANCE + TEST_AMOUNT), TEST_ACCOUNT_ID);
    }

    @Test
    public void deposit() {
        accountManager.updateAccountBalance (TEST_AMOUNT, TEST_ACCOUNT_ID);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?",
                Double.class, TEST_ACCOUNT_ID);
        assertEquals(balance, (INITIAL_BALANCE + TEST_AMOUNT), 0);
    }
    @Test
    public void withdraw() {
        accountManager.updateAccountBalance (-TEST_AMOUNT, TEST_ACCOUNT_ID);
        double balance = jdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?",
                Double.class, TEST_ACCOUNT_ID);
        assertEquals(balance, (INITIAL_BALANCE - TEST_AMOUNT), 0);
    }
}
