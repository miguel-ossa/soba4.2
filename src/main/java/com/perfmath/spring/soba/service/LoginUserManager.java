package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import com.perfmath.spring.soba.model.domain.LoginUser;


public interface LoginUserManager extends Serializable{

    public void createLoginUser(LoginUser user);
    
    public List<LoginUser> getLoginUsers();
    public String getCustomerIdByUsername( String username);
    public LoginUser getLoginUserByUsername( String username);
}
