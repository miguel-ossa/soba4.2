package com.perfmath.spring.soba.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.perfmath.spring.soba.model.domain.Account;

public class JdbcAccountDao  implements AccountDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public List<Account> getAccountList() {
		List<Account> accounts = this.jdbcTemplate.query("SELECT ACCOUNT_ID, NAME, TYPE, DESCRIPTION, STATUS, "
						+ " BALANCE, OPEN_DATE, CLOSE_DATE, CUSTOMER_ID FROM ACCOUNT",
						new AccountMapper());
		return accounts;
	}

	public String getAccountIdByCustomerId(String customerId, String accountType) {
		String sql = "SELECT ACCOUNT_ID FROM ACCOUNT WHERE CUSTOMER_ID = ? AND TYPE = ?";

		String accountId = this.jdbcTemplate.queryForObject(sql,
				String.class, customerId, accountType);
		return accountId;
	}

	public void insert(Account account) {
		String sql = "INSERT INTO ACCOUNT (ACCOUNT_ID, NAME, TYPE, DESCRIPTION, STATUS, "
				+ " BALANCE, OPEN_DATE, CLOSE_DATE, CUSTOMER_ID) "
				+ "VALUES (:accountId, :name, :type,:description, "
				+ ":status, :balance, :openDate, :closeDate, :customerId)";

		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				account);

		int count = this.namedParameterJdbcTemplate.update(sql, parameterSource);
	}

	public void insertBatch(List<Account> accounts) {
		String sql = "INSERT INTO ACCOUNT (ACCOUNT_ID, NAME, TYPE, DESCRIPTION, STATUS, "
				+ " BALANCE, OPEN_DATE, CLOSE_DATE, CUSTOMER_ID) "
				+ "VALUES (:accountId, :name, :type,:description, "
				+ ":status, :balance, :openDate, :closeDate, :customerId)";
		SqlParameterSource[]  batch = SqlParameterSourceUtils.createBatch(accounts.toArray());
		namedParameterJdbcTemplate.batchUpdate (sql, batch);
	}

	public Account findByAccountID(String accountID) {
		String sql = "SELECT ACCOUNT_ID, NAME, TYPE, DESCRIPTION, STATUS, BALANCE, "
				+ "  OPEN_DATE, CLOSE_DATE, CUSTOMER_ID FROM ACCOUNT WHERE ACCOUNT_ID = ?";
		try {
			Account account = this.jdbcTemplate.queryForObject(sql, new Object[]{accountID}, new AccountMapper());
			return account;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
    // to use this API, retrieve the existing account in the caller, reset the columns 
	// to be updated and pass the modified account object in
	public void update(Account account) {
		String sql = "UPDATE ACCOUNT SET NAME = ?, SET TYPE = ?," +
				" SET DESCRIPTION = ?, SET STATUS = ?, SET BALANCE = ?," +
				" SET OPEN_DATE = ?, SET CLOSE_DATE = ?, SET CUSTOMER_ID = ? " +
				" WHERE ACCOUNT_ID = ?";
		this.jdbcTemplate.update(sql, account.getName(), account.getType(), 
				account.getDescription(), account.getStatus(), account.getBalance(),
				account.getOpenDate(), account.getCloseDate(), account.getCustomerId(),
				account.getAccountId());
	}
	public double updateAccountBalance (double amount, String accountID) {
		String sql0 = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?";

		Double Balance = this.jdbcTemplate.queryForObject(sql0,
				Double.class, accountID);
		double currBalance = Balance.doubleValue();
		
		String sql1 = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
		int count = this.jdbcTemplate.update(sql1, (amount + currBalance), accountID);
		return (amount + currBalance);
	}

	public void delete(Account account) {
		String sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
		this.jdbcTemplate.update(sql, account.getAccountId());
	}

	public List<Map<String, Object>> findAll() {
		String sql = "SELECT * FROM ACCOUNT";

		List<Map<String, Object>> accounts = this.jdbcTemplate.queryForList(sql, new AccountMapper());
		return accounts;
	}

	public String getCustomerId(String accountID) {
		String sql = "SELECT CUSTOMER_ID FROM ACCOUNT WHERE ACCOUNT_ID = ?";

		String customerId = this.jdbcTemplate.queryForObject(sql,
				String.class, accountID);
		return customerId;
	}
	public double getBalance (String accountID) {
		String sql = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?";

		double balance = this.jdbcTemplate.queryForObject(sql, Double.class, accountID);
		return balance;
	}
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM ACCOUNT";

		int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
	private static class AccountMapper implements RowMapper<Account> {
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account();
			account.setAccountId(rs.getString("ACCOUNT_ID"));
			account.setName(rs.getString("NAME"));
			account.setType(rs.getString("TYPE"));
			account.setDescription(rs.getString("DESCRIPTION"));
			account.setStatus(rs.getString("STATUS"));
			account.setBalance(rs.getInt("BALANCE"));
			account.setOpenDate(rs.getTimestamp("OPEN_DATE"));
			account.setCloseDate(rs.getTimestamp("CLOSE_DATE"));
			account.setCustomerId(rs.getString("CUSTOMER_ID"));
			return account;
		}
	}
}
