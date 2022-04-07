package com.mcmaintank.androidapp.controller;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.GeocacheService;
import com.mcmaintank.androidapp.service.UserService;
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

    @RequestMapping(value = "getTopTenReportedUsers")
    @ResponseBody
    public List<User> getTopTenReportedUsers(@RequestBody Map o){
        String username = (String) o.get("username");
        if(userService.getUserGroupByName(username)==1){
            return userService.getTopTenReportedUsers();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "getTopTenReportedGeocaches")
    @ResponseBody
    public List<Geocache> getTopTenReportedGeocaches(@RequestBody Map o){
        String username = (String) o.get("username");
        if(userService.getUserGroupByName(username)==1){
            return geocacheService.getTopTenReportedGeocaches();
        }else{
            return null;
        }
    }

}
