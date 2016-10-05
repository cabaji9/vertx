package com.vertback.backend.daoImpl;

import com.vertback.backend.dao.UserDao;
import com.vertback.backend.vo.User;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by HW on 10/4/16.
 */
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public User obtainUserByUserName(String username) {
        User user = null;
        String jsonResult = obtainJsonResult("users.json");
        if (!jsonResult.isEmpty()) {
            List<User> listUser = obtainFromJsonList(jsonResult, User[].class);
            if (listUser != null) {
                for (User userBucle : listUser) {
                    if (username.equals(userBucle.getUser())) {
                        user = userBucle;
                    }
                }
            }
        }
        return user;
    }
}
