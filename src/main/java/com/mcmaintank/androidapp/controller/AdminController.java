package com.mcmaintank.androidapp.controller;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.GeocacheService;
import com.mcmaintank.androidapp.service.UserService;
import com.mcmaintank.androidapp.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    GeocacheService geocacheService;
    @Autowired
    UserService userService;

    EncryptUtil encryptUtil = new EncryptUtil();

    @RequestMapping(value = "getTopTenReportedUsers")
    @ResponseBody
    public List<User> getTopTenReportedUsers(@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        if(userService.getUserGroupByName(username)==1&&(userService.getPassword(username)).equals(encryptUtil.encrypt(password))){
            return userService.getTopTenReportedUsers();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "getTopTenReportedGeocaches")
    @ResponseBody
    public List<Geocache> getTopTenReportedGeocaches(@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        if(userService.getUserGroupByName(username)==1&&(userService.getPassword(username)).equals(encryptUtil.encrypt(password))){
            return geocacheService.getTopTenReportedGeocaches();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "deleteGeocache")
    @ResponseBody
    public String deleteGeocache(@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        String geocacheIdString = (String) o.get("geocacheId");
        if((userService.getUserGroupByName(username)==1||geocacheService.getGeocache(Long.parseLong(geocacheIdString)).getPid()==userService.getUserByName(username).getUserId())&&(userService.getPassword(username)).equals(encryptUtil.encrypt(password))){
            geocacheService.logicDeleteGeocache(Long.parseLong(geocacheIdString));
            String jsonString="{\"kstatus\":1}";
            return jsonString;
        }else{
            String jsonString="{\"kstatus\":0}";
            return jsonString;
        }
    }

    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        String userIdString = (String) o.get("userId");
        if(userService.getUserGroupByName(username)==1&&(userService.getPassword(username)).equals(encryptUtil.encrypt(password))){
            userService.logicDeleteUser(Long.parseLong(userIdString));
            String jsonString="{\"kstatus\":1}";
            return jsonString;
        }else{
            String jsonString="{\"kstatus\":0}";
            return jsonString;
        }
    }

}
