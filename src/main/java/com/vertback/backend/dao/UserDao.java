package com.vertback.backend.dao;

import com.vertback.backend.vo.User;

/**
 * Created by HW on 10/4/16.
 */
public interface UserDao {


    public User obtainUserByUserName(String userName);

}
