package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-02-19 0:31
 */
@Import(UserService.class)
class UserServiceTest {

    @Autowired
    UserService userService = new UserServiceImpl();

    @Test
    void getUserTest() {
//        userService.getUser(Long.valueOf(1));
    }

    @Test
    void getUserByName() {

    }

    @Test
    void getPassword() {

    }

    @Test
    void createUser() {
//        User user= new User();
//        user.setUserName("user258");
//        user.setUserPassword("123456");
//        user.setUserGroup(0);
//        userService.createUser(user);
//        assertEquals(user,userService.getUserByName("user258"));
    }
}