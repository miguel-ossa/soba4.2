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

import com.perfmath.spring.soba.model.domain.LoginUser;

public class JdbcLoginUserDao implements LoginUserDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<LoginUser> getLoginUserList() {
        List<LoginUser> loginUsers = this.jdbcTemplate.query(
                "SELECT USERNAME, PASSWORD, ENABLED, CREATE_DATE, CLOSE_DATE, "
        		+ "CUSTOMER_ID FROM LOGINUSER", 
                new LoginUserMapper());
        return loginUsers;
    }
    public LoginUser getLoginUserByUsername(String name) {
        LoginUser loginUser = this.jdbcTemplate.queryForObject(
                "SELECT USERNAME, PASSWORD, ENABLED, CREATE_DATE, CLOSE_DATE, "
        		+ "CUSTOMER_ID FROM LOGINUSER WHERE USERNAME = '" + name + "'", 
                new LoginUserMapper());
        return loginUser;
    }
    public void insert(LoginUser user) {
        String sql = "INSERT INTO LOGINUSER (USERNAME, PASSWORD, ENABLED, CREATE_DATE,"
        		+ "CLOSE_DATE, CUSTOMER_ID )" 
                + "VALUES (:username, :password, :enabled, :createDate, :closeDate,"
                + ":customerId)";
     
        SqlParameterSource parameterSource =
            new BeanPropertySqlParameterSource(user);

        this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }
    public String getCustomerIdByUsername(String username) {
        String sql = "SELECT CUSTOMER_ID FROM LOGINUSER WHERE USERNAME = ?";

        String customerId = this.jdbcTemplate.queryForObject(sql,
        		String.class, username);
        return customerId;
    }
    public String getUsernameByCustomerId(String customerId) {
        String sql = "SELECT USERNAME FROM LOGINUSER WHERE CUSTOMER_ID = ?";

        String username = this.jdbcTemplate.queryForObject(sql,
        		String.class, customerId);
        return username;
    }

    public void update(LoginUser user) {}

    public void delete(LoginUser user) {}

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM LOGINUSER";

        List<Map<String,Object>> users = this.jdbcTemplate.queryForList(sql, new LoginUserMapper());
        return users;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM LOGINUSER";

        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
    private static class LoginUserMapper implements RowMapper<LoginUser> {
    	public LoginUser mapRow(ResultSet rs, int rowNum) throws SQLException {
    		LoginUser user = new LoginUser();
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setEnabled(rs.getInt("ENABLED"));
            user.setCustomerId(rs.getString("CUSTOMER_ID"));
            user.setCreateDate(rs.getTimestamp("CREATE_DATE"));
            user.setCloseDate(rs.getTimestamp("CLOSE_DATE"));
            return user;
        }
    }
 
}

