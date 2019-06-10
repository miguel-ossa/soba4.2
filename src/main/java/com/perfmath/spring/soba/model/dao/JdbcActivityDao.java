package com.perfmath.spring.soba.model.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.cache.annotation.Cacheable;

import com.perfmath.spring.soba.model.domain.Activity;

public class JdbcActivityDao implements ActivityDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Cacheable (value="activities", key="#customerId")
    public List<Activity> getActivities(String customerId, String accountType) {
		long periodSeconds = 30 * 24 * 3600 * 1000L;
        String sql = "SELECT * FROM ACTIVITY WHERE CUSTOMER_ID='" + customerId +
        "' AND ACCOUNT_TYPE = '" + accountType + "' ORDER BY TRANS_DATE DESC";
        List<Activity> activities = this.jdbcTemplate.query(sql, new ActivityMapper());

        return activities;
    }

	public List<Activity> getActivities(String customerId, String accountType,
			String periodInDays) {
		long periodSeconds = Long.parseLong(periodInDays) * 24 * 3600 * 1000L;
		Timestamp pastTime = new Timestamp (System.currentTimeMillis() - periodSeconds);
        String sql = "SELECT * FROM ACTIVITY WHERE CUSTOMER_ID='" + customerId +
        "' AND ACCOUNT_TYPE = '" + accountType + "' AND TRANS_DATE >= {ts '" + pastTime +
         "'} ORDER BY TRANS_DATE DESC";
        List<Activity> activities = this.jdbcTemplate.query(sql, new ActivityMapper());

        return activities;
	}

	public List<Activity> getActivities(String customerId, String accountType,
			String from, String to) {
        String sql = "SELECT * FROM ACTIVITY WHERE CUSTOMER_ID='" + customerId +
        "' AND ACCOUNT_TYPE = '" + accountType  + "' AND TRANS_DATE >= {ts '" + from +
        "'} AND TRANS_DATE <= {ts '" + to + "'}  ORDER BY TRANS_DATE DESC";
        List<Activity> activities = this.jdbcTemplate.query(sql, new ActivityMapper());

        return activities;
	}
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM ACTIVITY";

        List<Map<String,Object>> activities = this.jdbcTemplate.queryForList(sql, new ActivityMapper ());
        return activities;
    }


    public int countAll() {
        String sql = "SELECT COUNT(*) FROM ACTIVITY";

        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
    private static class ActivityMapper implements RowMapper<Activity> {
    	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Activity activity = new Activity();
    		activity.setCustomerId(rs.getString("CUSTOMER_ID"));
    		activity.setAccountId(rs.getString("ACCOUNT_ID"));
    		activity.setTransactionId(rs.getString("TRANSACTION_ID"));
    		activity.setName(rs.getString("NAME"));
    		activity.setAccountType(rs.getString("ACCOUNT_TYPE"));
    		activity.setTxType(rs.getString("TX_TYPE"));
    		activity.setTransDate(rs.getTimestamp("TRANS_DATE"));
    		activity.setInitiator(rs.getString("INITIATOR"));
    		activity.setDescription(rs.getString("DESCRIPTION"));
    		activity.setAmount(rs.getDouble("AMOUNT"));
    		activity.setBalance(rs.getDouble("BALANCE"));
    		activity.setStatus(rs.getString("STATUS"));
            return activity;
        }
    }
 
}

