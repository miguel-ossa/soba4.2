package com.perfmath.spring.soba.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.perfmath.spring.soba.model.domain.BankingTx;

public class JdbcAclBankingTxDao implements AclBankingTxDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<BankingTx> getTransactionList() {
        List<BankingTx> txs = this.jdbcTemplate.query(
                "SELECT TRANSACTION_ID, TRANS_DATE, TYPE, " 
        		+ " INITIATOR, DESCRIPTION, AMOUNT, BALANCE, ACCOUNT_ID,"
        		+ " STATUS FROM BANKINGTX", 
                new TransactionMapper());
        return txs;
    }
	
    public void insert(BankingTx transaction) {
        String sql = "INSERT INTO BANKINGTX (TRANSACTION_ID, TRANS_DATE, TYPE,"
        	+"INITIATOR, DESCRIPTION, AMOUNT, BALANCE, ACCOUNT_ID,"
        		+ " STATUS) " 
                + "VALUES (:transactionId, :transDate, :type, :initiator, "
                + ":description, :amount, :balance, :accountId, :status)";
     
        SqlParameterSource parameterSource =
            new BeanPropertySqlParameterSource(transaction);

        int count = this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public void insertBatch(List<BankingTx> trans) {
        String sql = "INSERT INTO BANKINGTX (TRANSACTION_ID, TRANS_DATE, TYPE,"
        	+"INITIATOR, DESCRIPTION, AMOUNT, BALANCE, ACCOUNT_ID, STATUS) " 
                + "VALUES (:transactionId, :transDate, :type, :initiator, "
                + ":description, :amount, :balance, :accountId, :status)";

        SqlParameterSource[]  batch = SqlParameterSourceUtils.createBatch(trans.toArray());
		namedParameterJdbcTemplate.batchUpdate (sql, batch);
    }

    public BankingTx findByTransactionID(String transID) {
    	
        String sql = "SELECT TRANSACTION_ID, TRANS_DATE, TYPE,"
        	+"INITIATOR, DESCRIPTION, AMOUNT, BALANCE, ACCOUNT_ID, STATUS"
        	+ " FROM BANKINGTX WHERE TRANSACTION_ID = ?";
        BankingTx trans = (BankingTx) this.jdbcTemplate.queryForObject(sql,
        		BeanPropertyRowMapper.newInstance(BankingTx.class), transID);
        return trans;
   
    }

    public void update(BankingTx tx) {
    }

    public void delete(String txId) {
        String sql = "DELETE BANKINGTX WHERE TRANSACTION_ID = ?";

        int count = this.jdbcTemplate.update(sql, txId);
    }

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM TRANSACTION";

        List<Map<String,Object>> trans = this.jdbcTemplate.queryForList(sql, new TransactionMapper());
        return trans;
    }

    public String getAccountId(String transID) {
        String sql = "SELECT ACCOUNT_ID FROM TRANSACTION WHERE TRANSACTION_ID = ?";

        String accountId = this.jdbcTemplate.queryForObject(sql,
                String.class, transID);
        return accountId;
    }
    public List<BankingTx> getTransactions(String accountId) {
	long periodSeconds = 30 * 24 * 3600 * 1000L;
    String sql = "SELECT * FROM BANKINGTX WHERE ACCOUNT_ID='" + accountId + "' ORDER BY TRANS_DATE DESC";
    List<BankingTx> transactions = this.jdbcTemplate.query(sql, new TransactionMapper());

    return transactions;
    }
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM BANKINGTX";

        int count = this.jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
    private static class TransactionMapper implements RowMapper<BankingTx> {
    	public BankingTx mapRow(ResultSet rs, int rowNum) throws SQLException {
    		BankingTx tx = new BankingTx();
    		tx.setTransactionId(rs.getLong("TRANSACTION_ID"));
    		tx.setTransDate(rs.getTimestamp("TRANS_DATE"));
    		tx.setType(rs.getString("TYPE"));
    		tx.setInitiator(rs.getString("INITIATOR"));
    		tx.setDescription(rs.getString("DESCRIPTION"));
    		tx.setAmount(rs.getDouble("AMOUNT"));
    		tx.setBalance(rs.getDouble("BALANCE"));
    		tx.setAccountId(rs.getString("ACCOUNT_ID"));
    		tx.setStatus(rs.getString("STATUS"));
            return tx;
        }
    }
}

