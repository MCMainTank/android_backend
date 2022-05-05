package com.mcmaintank.androidapp.controller;

import com.mcmaintank.androidapp.model.Activity;
import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.GeocacheService;
import com.mcmaintank.androidapp.service.UserService;
import com.mcmaintank.androidapp.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * @author MCMainTank
 * @date 2022-01-11 0:34
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GeocacheService geocacheService;

    EncryptUtil encryptUtil = new EncryptUtil();


    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "Welcome! Please log in.";
    }

    @RequestMapping(path = "/login")
    @ResponseBody
    public String login(/*@RequestParam(value="username",required=false) String username,
                        @RequestParam(value="password",required=false) String password
                        *//*Map<String,Object> map*/@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");

        if(!StringUtils.isEmpty(username)&&(userService.getPassword(username)).equals(encryptUtil.encrypt(password))&&userService.getUserGroupByName(username)==0&&userService.getDeleted(username)==0){
            String jsonString1 = "{\"kstatus\":1}";
            return jsonString1;
        }else if(!StringUtils.isEmpty(username)&&(userService.getPassword(username)).equals(encryptUtil.encrypt(password))&&userService.getUserGroupByName(username)==1){
            String jsonString1 = "{\"kstatus\":2}";
            return jsonString1;
        }else{
            System.out.println(username+password);
            String jsonString2="{\"kstatus\":0}";
            return jsonString2;

        }

    }



    @RequestMapping(value = "getUser")
    @ResponseBody
    public User checkUserInfo(@RequestParam("userId") Long userId){
        User user = userService.getUser(userId);
        return user;
    }

    @RequestMapping(value = "getUserByName")
    @ResponseBody
    public User checkUserByName(@RequestBody Map o){
        String username = (String)o.get("username");
        User user = userService.getUserByName(username);
        return user;
    }

    @RequestMapping("register")
    @ResponseBody
    public String register(/*@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("repass") String repass,
                          @RequestParam("userEmail") String userEmail,
                          @RequestParam("userCellphone") Long userCellphone*/
            @RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");

        if(!StringUtils.isEmpty(username)) {
            if (userService.getUserByName(username) == null){
                if (!StringUtils.isEmpty(password) && (password).length() >= 6) {
                                User user = new User();
                                user.setUserName(username);
                                user.setUserPassword(encryptUtil.encrypt(password));
                                user.setUserGroup(0);
                                if(userService.createUser(user)==1){
                                    String jsonString1 = "{\"kstatus\":1}";
                                    return jsonString1;
                                }else {
                                    String jsonString2="{\"kstatus\":0}";
                                    return jsonString2;
                                }
                } else {
//                    return "Please enter a password!";
                    String jsonString2="{\"kstatus\":0}";
                    return jsonString2;
                }

            }else {
//                return "Username taken!";
                String jsonString2="{\"kstatus\":3}";
                return jsonString2;
            }
        }else{
//            return "Please enter a username!";
            String jsonString2="{\"kstatus\":0}";
            return jsonString2;
        }
//        return "username taken";
//        return "success";
    }

//    @RequestMapping("searchForUser")
//    @ResponseBody
//    public User searchForUser(@RequestBody Map o){
//        Integer isAdmin = parseInt((String) o.get("isAdmin"));
//        if(isAdmin==1){
//            return userService.getUser(Long.parseLong((String) o.get("userId")));
//        }
//        else return null;
//    }

//    @RequestMapping("deleteUser")
//    @ResponseBody
//    public String deleteUser(@RequestBody Map o){
//        Integer isAdmin = parseInt((String) o.get("isAdmin"));
//        if(isAdmin==1){
//            if(userService.logicDeleteUser(Long.parseLong((String) o.get("userId")))==1){
//                String jsonString1 = "{\"kstatus\":1}";
//                return jsonString1;
//            } else {
//                String jsonString1 = "{\"kstatus\":2}";
//                return jsonString1;
//            }
//        } else {
//            String jsonString1 = "{\"kstatus\":0}";
//            return jsonString1;
//        }
//
//    }

    @RequestMapping(value = "createActivity")
    @ResponseBody
    public String createGeocache(@RequestBody Map o){
        Activity activity = new Activity();
        Integer sign = 0;
        activity.setActivityContent((String) o.get("contents"));
        activity.setActivityType((String) o.get("type"));
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        activity.setUserId(userService.getUserByName(username).getUserId());
        activity.setGeocacheId(Long.parseLong((String) o.get("geocacheId")));
        activity.setDeleted(false) ;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate = new Date();
        try{
            createDate = df.parse(df.format(new Date()));
        }catch(Exception e){
            e.printStackTrace();
        }
        activity.setActivityDateOfUpload(createDate);
        User user = userService.getUserByName((String) o.get("username"));
        List<Activity> activityList = new ArrayList<Activity>();
        activityList = userService.getUserActivity(username);
        for(int i=0;i<activityList.size();i++){
            if(activityList.get(i).getGeocacheId()==activity.getGeocacheId()){
                sign=1;
            }
        }
        if((geocacheService.getGeocache(activity.getGeocacheId()).getPid()==userService.getUserByName(username).getUserId())&&sign==0) {
            return "{\"generatedKey\":-1}";
        } else if(sign==1){
            return "{\"generatedKey\":-2}";
        } else if((userService.createActivity(activity)==1&&((userService.getPassword(username)).equals(encryptUtil.encrypt(password))&&userService.getDeleted(username)==0))&&geocacheService.getGeocache(activity.getGeocacheId()).getPid()!=userService.getUserByName(username).getUserId()&&sign==0) {
            String jsonString = "{\"generatedKey\":\""+userService.getLatestActivityIdByUser((String) o.get("username"))+"\"}";
            return jsonString;
        }else{
            return "{\"generatedKey\":0}";
        }
    }

    @RequestMapping(value = "getActivity")
    @ResponseBody
    public List<Activity> pullActivity(@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        if((userService.getPassword(username)).equals(encryptUtil.encrypt(password))&&userService.getDeleted(username)==0) {
            return userService.getUserActivity((String) o.get("username"));
        } else {
            return null;
        }
    }



}