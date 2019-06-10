package com.perfmath.spring.soba.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.perfmath.spring.soba.model.domain.BankingTx;
import com.perfmath.spring.soba.model.domain.Transfer;

public class JdbcTransferDao implements TransferDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<Transfer> getTransferList() {
        List<Transfer> transfers = this.jdbcTemplate.query(
                "SELECT TRANSFER_ID, TRANSFER_DATE, FROM_ACCOUNT_ID, " 
        		+ " TO_ACCOUNT_ID, FROM_TX_ID, TO_TX_ID, INITIATOR, "
        		+ " DESCRIPTION, AMOUNT FROM TRANSFER", 
                new TranasferMapper());
        return transfers;
    }
	
    public void insert(Transfer transfer) {
        String sql = "INSERT INTO TRANSFER (TRANSFER_ID, TRANSFER_DATE,"
        	+"FROM_ACCOUNT_ID, TO_ACCOUNT_ID, FROM_TX_ID, TO_TX_ID, INITIATOR, "
        	+ " DESCRIPTION, AMOUNT ) " 
            + "VALUES (:transferId, :transferDate, :fromAccountId, :toAccountId, "
            + ":fromTxId, :toTxId, :initiator, :description, :amount)";
     
        SqlParameterSource parameterSource =
            new BeanPropertySqlParameterSource(transfer);

        int count = this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }
    public void update(Transfer transfer) {
    }

    public void insertBatch(List<Transfer> tranfers) {
    	 String sql = "INSERT INTO TRANSFER (TRANSFER_ID, TRANSFER_DATE,"
         	+"FROM_ACCOUNT_ID, TO_ACCOUNT_ID, FROM_TX_ID, TO_TX_ID, INITIATOR, "
         	+ " DESCRIPTION, AMOUNT) " 
             + "VALUES (:transferId, :transferDate, :fromAccountId, :toAccountId, "
             + ":fromTxId, :toTxId, :initiator, :description, :amount)";
      

    	 SqlParameterSource[]  batch = SqlParameterSourceUtils.createBatch(tranfers.toArray());
 		namedParameterJdbcTemplate.batchUpdate (sql, batch);
    }

    
    public Transfer findByFromTxID(String fromTxId) {
        String sql = "SELECT * FROM TRANSFER WHERE FROM_TX_ID = ?";

        Transfer transfer = this.jdbcTemplate.queryForObject(sql,
        		Transfer.class, fromTxId);
        return transfer;
    }

    public void update(BankingTx tx) {
    }

    public void delete(String transferId) {
        String sql = "DELETE TRANSFER WHERE TRANSFER_ID = ?";

        int count = this.jdbcTemplate.update(sql, transferId);
    }

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM TRANSFER";

        List<Map<String,Object>> trans = this.jdbcTemplate.queryForList(sql, new TranasferMapper());
        return trans;
    }

    public List<Transfer> findByFromAccountId(String fromAccountId) {
        String sql = "SELECT ACCOUNT_ID FROM TRANSFER WHERE FROM_ACCOUNT_ID = '" 
        	+ fromAccountId + "'";

        List<Transfer>  transfers = this.jdbcTemplate.query(sql,
        		new TranasferMapper());
        return transfers;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM TRANSACTION";

        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
    private static class TranasferMapper implements RowMapper<Transfer> {
    	public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Transfer tsf = new Transfer();
    		tsf.setTransferId(rs.getInt("TRANSFER_ID"));
    		tsf.setFromAccountId(rs.getString("FROM_ACCOUNT_ID"));
    		tsf.setToAccountId(rs.getString("TO_ACCOUNT_ID"));
    		tsf.setFromTxId(rs.getInt("FROM_TX_ID"));
    		tsf.setToTxId(rs.getInt("To_TX_ID"));
    		tsf.setInitiator(rs.getString("INITIATOR"));
    		tsf.setDescription(rs.getString("DESCRIPTION"));
    		tsf.setAmount(rs.getInt("AMOUNT"));
    		tsf.setTransferDate(rs.getTimestamp("TRANSFER_DATE"));
            
            return tsf;
        }
    }
}

