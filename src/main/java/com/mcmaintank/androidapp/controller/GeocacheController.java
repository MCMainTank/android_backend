package com.mcmaintank.androidapp.controller;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.GeocacheService;
import com.mcmaintank.androidapp.service.UserService;
import com.mcmaintank.androidapp.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-02-18 15:49
 */
@Controller
public class GeocacheController {

    @Autowired
    GeocacheService geocacheService;
    @Autowired
    UserService userService;

    EncryptUtil encryptUtil = new EncryptUtil();


    @RequestMapping(value = "getGeocache")
    @ResponseBody
    public Geocache getGeocache(@RequestBody Map o){
        String geocacheIdString = (String) o.get("geocacheId");
        Geocache geocache = geocacheService.getGeocache(Long.parseLong(geocacheIdString));
        if(geocacheService.getDeleted(Long.parseLong(geocacheIdString))==0)
            return geocache;
        else
            return null;
    }

    @RequestMapping(value = "updateGeocache")
    @ResponseBody
    public String updateGeocache(@RequestBody Map o) {
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        String geocacheIdString = (String) o.get("geocacheId");
        String geocacheLocationDescription = (String) o.get("description");
        if(geocacheService.getGeocache(Long.parseLong(geocacheIdString)).getPid()==userService.getUserByName(username).getUserId()&&userService.getPassword(username).equals(encryptUtil.encrypt(password))){
            geocacheService.changeGeocacheById(Long.parseLong(geocacheIdString),geocacheLocationDescription);
            String jsonString = "{\"kstatus\":1}";
            return jsonString;
        }else{
            String jsonString = "{\"kstatus\":0}";
            return jsonString;
        }

    }

    @RequestMapping(value = "getGeocacheByUserId")
    @ResponseBody
    public List<Geocache> getGeocacheByUserName(@RequestBody Map o){
        String username = (String) o.get("username");
        Long pid = userService.getUserByName(username).getUserId();
        List<Geocache> geocacheList = geocacheService.getGeocacheByUser(pid);
        return geocacheList;
    }

    @RequestMapping(value = "createGeocache")
    @ResponseBody
    public String createGeocache(@RequestBody Map o){
        Geocache geocache = new Geocache();
        geocache.setGeocacheLatitudes(Double.valueOf((String) o.get("Latitudes")).doubleValue());
        geocache.setGeocacheLongitudes(Double.valueOf((String) o.get("Longitudes")).doubleValue());
        geocache.setGeocacheLocationDescription((String) o.get("Description"));
        geocache.setDeleted(false) ;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate = new Date();
        try{
            createDate = df.parse(df.format(new Date()));
        }catch(Exception e){
            e.printStackTrace();
        }
        geocache.setGeocacheDateOfUpload(createDate);
        User user = userService.getUserByName((String) o.get("username"));
        geocache.setPid(user.getUserId());
        if(geocacheService.createGeocacheEntry(geocache)==1) {
            String jsonString = "{\"generatedKey\":\""+geocacheService.getLatestGeocacheIdByUser(user.getUserId())+"\"}";
            return jsonString;
        } else {
            return "{\"generatedKey\":0}";
        }
    }

//    @RequestMapping("deleteGeocache")
//    @ResponseBody
//    public String deleteGeocache(@RequestBody Map o){
//        Integer isAdmin = parseInt((String) o.get("isAdmin"));
//        if(isAdmin==1){
//            if(geocacheService.logicDeleteGeocache(Long.parseLong((String)o.get("geocacheId")))==1){
//                String jsonString1 = "{\"kstatus\":1}";
//                return jsonString1;
//            }else{
//                String jsonString1 = "{\"kstatus\":2}";
//                return jsonString1;
//            }
//        } else {
//            String jsonString1 = "{\"kstatus\":0}";
//            return jsonString1;
//        }
//
//    }

    @RequestMapping(value = "getNearestGeocache")
    @ResponseBody
    public List<Geocache> getNearestGeocache(@RequestBody Map o){
        List<Geocache> geocacheList = geocacheService.getNearestGeocache(Double.valueOf((String) o.get("Latitudes")).doubleValue(),Double.valueOf((String) o.get("Longitudes")).doubleValue());
        return geocacheList;
    }

    @RequestMapping(value = "reportGeocache")
    @ResponseBody
    public String reportGeocache(@RequestBody Map o){
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        String geocacheIdString = (String) o.get("geocacheId");
        if((userService.getPassword(username)).equals(encryptUtil.encrypt(password))&&userService.getDeleted(username)==0){
            geocacheService.reportGeocache(Long.parseLong(geocacheIdString));
            String jsonString = "{\"kstatus\":1}";
            return jsonString;
        }else{
            String jsonString = "{\"kstatus\":0}";
            return jsonString;
        }
    }


}
