package com.perfmath.spring.soba.service;

import java.util.List;

import com.perfmath.spring.soba.model.dao.LoginUserDao;
import com.perfmath.spring.soba.model.domain.LoginUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SimpleLoginUserManager implements LoginUserManager {

    private LoginUserDao loginUserDao;

    public List<LoginUser> getLoginUsers() {
        return loginUserDao.getLoginUserList();
    }

    public void createLoginUser(LoginUser user) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String bcryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(bcryptedPassword);
    	loginUserDao.insert(user);
    }

    public void setLoginUserDao(LoginUserDao loginUserDao) {
        this.loginUserDao = loginUserDao;
    }
    public String getCustomerIdByUsername(String username)
    {
    	return loginUserDao.getCustomerIdByUsername(username);
    }
    public LoginUser getLoginUserByUsername( String username) {
    	return loginUserDao.getLoginUserByUsername(username);
    }
    public String getUsernameByCustomerId(String customerId) {
    	return loginUserDao.getUsernameByCustomerId(customerId);
    }
}
