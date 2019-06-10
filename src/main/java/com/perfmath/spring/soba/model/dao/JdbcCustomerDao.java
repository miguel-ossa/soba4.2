package com.perfmath.spring.soba.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.perfmath.spring.soba.model.domain.Customer;

public class JdbcCustomerDao implements CustomerDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<Customer> getCustomerList() {
        String sql = "SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, CITY, STATE, " 
    		+ " ZIPCODE, EMAIL, STATUS, CREATE_DATE FROM CUSTOMER";
        List<Customer> customers = this.jdbcTemplate.query(sql, new CustomerMapper());

        return customers;
    }

    public void insert(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, PHONE,  ADDRESS, CITY, STATE, " 
        		+ " ZIPCODE, EMAIL, STATUS, CREATE_DATE) "
                + "VALUES (:customerId, :firstName, :lastName, :phone, :address, :city, "
                + ":state, :zipcode, :email, :status, :createDate)";
     
        SqlParameterSource parameterSource =
            new BeanPropertySqlParameterSource(customer);

        this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public void insertBatch(List<Customer> customers) {
        String sql = "INSERT INTO Customer (CUSTOMER_ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, CITY, STATE, " 
    		+ " ZIPCODE, EMAIL, STATUS, CREATE_DATE) "
            + "VALUES (:customerID, :firstName, :lastName, :phone, :address, :city, "
            + ":state, :zipcode, :email, :status, :createDate)";

        SqlParameterSource[]  batch = SqlParameterSourceUtils.createBatch(customers.toArray());
		namedParameterJdbcTemplate.batchUpdate (sql, batch);
    }

    public Customer findByCustomerID(String customerID) {
       /*
    	String sql = "SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, CITY, STATE, " 
    		+ " ZIPCODE, EMAIL, STATUS, CREATE_DATE FROM CUSTOMER WHERE CUSTOMER_ID = ?";

        Customer customer = this.jdbcTemplate.queryForObject(sql,
                Customer.class, customerID);
        return customer;
        */
    	String sql = "SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, CITY, STATE, " 
        		+ " ZIPCODE, EMAIL, STATUS, CREATE_DATE FROM CUSTOMER WHERE CUSTOMER_ID = '" + customerID + "'";

            Customer customer = this.jdbcTemplate.queryForObject(sql,
                    new CustomerMapper());
            return customer;
    }

    public void update(Customer customer) {}

    public void delete(Customer customer) {}

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM CUSTOMER";

        List<Map<String,Object>> customers = this.jdbcTemplate.queryForList(sql, new CustomerMapper());
        return customers;
    }

    public String getEmail(String customerID) {
        String sql = "SELECT EMAIL FROM CUSTOMER WHERE CUSTOMER_ID = ?";

        String email = this.jdbcTemplate.queryForObject(sql,
                String.class, customerID);
        return email;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM CUSTOMER";

        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
    private static class CustomerMapper implements RowMapper<Customer> {
    	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Customer cust = new Customer();
            cust.setCustomerId(rs.getString("CUSTOMER_ID"));
            cust.setFirstName(rs.getString("FIRST_NAME"));
            cust.setLastName(rs.getString("LAST_NAME"));
            cust.setPhone(rs.getString("PHONE"));
            cust.setAddress(rs.getString("ADDRESS"));
            cust.setCity(rs.getString("CITY"));
            cust.setState(rs.getString("STATE"));
            cust.setZipcode(rs.getString("ZIPCODE"));
            cust.setEmail(rs.getString("EMAIL"));
            cust.setStatus(rs.getInt("STATUS"));
            cust.setCreateDate(rs.getTimestamp("CREATE_DATE"));
            return cust;
        }
    }
 
}

