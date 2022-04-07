package com.mcmaintank.androidapp.service.impl;

import com.mcmaintank.androidapp.mapper.UserMapper;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-9 16:13
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String SALT = "MCMTproduce5Fx";

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(Long userId) {
        User user = userMapper.selectUserById(userId);
        return user;
    }

    @Override
    public User getUserByName(String username) {
        User user = userMapper.selectUserByName(username);
        return user;
    }

    @Override
    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    @Override
    public int createUser(User user) {
        if (userMapper.insertUser(user)==1){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int getUserGroupByName(String username) {
        return userMapper.selectUserGroupByName(username);
    }

    @Override
    public List<User> getTopTenReportedUsers() {
        return userMapper.selectTopTenReportedUsers();
    }

    @Override
    public int getDeleted(String username){
        return userMapper.getDeleted(username);
    }

    @Override
    public int logicDeleteUser(Long userId){
        return userMapper.logicDeleteUser(userId);
    }

}
