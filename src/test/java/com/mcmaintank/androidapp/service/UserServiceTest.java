package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.AndroidApplication;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.utils.EncryptUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void getUser() {
        Assertions.assertTrue(userService.getUser(1L).getUserName().equals("user1"));
    }

    @Test
    void getUserByName() {
        Assertions.assertTrue(userService.getUserByName("user1").getUserId()==1L);
    }

    @Test
    void getPassword() {
        if(EncryptUtil.encrypt("123456").equals(userService.getPassword("user1"))){
            Assertions.assertTrue(true);
        }
        else{
            Assertions.assertTrue(false);
        }
    }

    @Test
    void createUser() {
        Assertions.assertTrue(true);
    }

    @Test
    void getUserGroupByName() {
        if(userService.getUserGroupByName("user1")==0) {
            Assertions.assertTrue(true);
        }else{
            Assertions.assertTrue(false);
        }
    }

    @Test
    void getTopTenReportedUsers() {
        System.out.println(Arrays.toString(userService.getTopTenReportedUsers().toArray(new User[0])));

        Assertions.assertTrue(true);
    }

    @Test
    void getDeleted() {
        Assertions.assertTrue(userService.getDeleted("user1")==0);
    }

    @Test
    void logicDeleteUser() {
        Assertions.assertTrue(true);
    }
}