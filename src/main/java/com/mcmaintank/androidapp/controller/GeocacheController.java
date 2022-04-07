package com.mcmaintank.androidapp.controller;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.GeocacheService;
import com.mcmaintank.androidapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

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

    @RequestMapping(value = "getGeocacheByUserId")
    @ResponseBody
    public List<Geocache> getGeocacheByUserId(@RequestParam("pid") Long pid){
        List<Geocache> geocacheList = geocacheService.getGeocacheByUser(pid);
        return geocacheList;
    }

    @RequestMapping(value = "createGeocache")
    @ResponseBody
    public int createGeocache(@RequestBody Map o){
        Geocache geocache = new Geocache();
        geocache.setGeocacheLatitudes((Double) o.get("Latitudes"));
        geocache.setGeocacheLongitudes((Double) o.get("Longitudes"));
        geocache.setGeocacheLocationDescription((String) o.get("Description"));
        geocache.setDeleted(false) ;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate = new Date();
        try{
            createDate = df.parse(df.format(new Date()));
        }catch(Exception e){
            e.printStackTrace();
        }
        geocache.setGeocacheDateOfUpload((java.sql.Date) createDate);
        User user = userService.getUserByName((String) o.get("username"));
        geocache.setPid(user.getUserId());
        if(geocacheService.createGeocacheEntry(geocache)==1) {
            return geocacheService.getLatestGeocacheIdByUser(user.getUserId());
        } else {
            return 0;
        }
    }

    @RequestMapping("deleteGeocache")
    @ResponseBody
    public String deleteGeocache(@RequestBody Map o){
        Integer isAdmin = parseInt((String) o.get("isAdmin"));
        if(isAdmin==1){
            if(geocacheService.logicDeleteGeocache(Long.parseLong((String)o.get("geocacheId")))==1){
                String jsonString1 = "{\"kstatus\":1}";
                return jsonString1;
            }else{
                String jsonString1 = "{\"kstatus\":2}";
                return jsonString1;
            }
        } else {
            String jsonString1 = "{\"kstatus\":0}";
            return jsonString1;
        }

    }

    @RequestMapping(value = "getNearestGeocache")
    @ResponseBody
    public Geocache getNearestGeocache(@RequestParam Map o){
        Geocache geocache = geocacheService.getNearestGeocache((Double)o.get("Latitudes"),(Double) o.get("Longitudes"));
        return geocache;
    }


}
