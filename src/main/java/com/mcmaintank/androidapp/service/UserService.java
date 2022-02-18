package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.User;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-9 16:12
 */
public interface UserService {

    public User getUser(Long userId);

    public User getUserByName(String username);

    public String getPassword(String username);

    public int createUser(User user);
}
