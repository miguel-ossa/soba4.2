package com.perfmath.spring.soba.model.dao;

import java.util.List;
import java.util.Map;

import com.perfmath.spring.soba.model.domain.LoginUser;

public interface LoginUserDao {
	public List<LoginUser> getLoginUserList();
	public LoginUser getLoginUserByUsername(String username);
    public void insert(LoginUser loginUser);
    public void update(LoginUser loginUser);
    public void delete(LoginUser loginUser);
    public String getCustomerIdByUsername(String username);
    public String getUsernameByCustomerId(String customerId);
    public List<Map<String, Object>> findAll();
    public int countAll();
}
