package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.Activity;
import com.mcmaintank.androidapp.model.User;

import java.util.List;

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

    public int getUserGroupByName(String username);

    public List<User> getTopTenReportedUsers();

    int getDeleted(String username);

    int logicDeleteUser(Long userId);

    public int createActivity(Activity activity);

    public int getLatestActivityIdByUser(String username);

    public List<Activity> getUserActivity(String username);
}
