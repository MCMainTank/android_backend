package com.mcmaintank.androidapp.controller;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import com.mcmaintank.androidapp.service.GeocacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-02-18 15:49
 */
@Controller
public class GeocacheController {

    @Autowired
    GeocacheService geocacheService;

    @RequestMapping(value = "getGeocache")
    @ResponseBody
    public Geocache getGeocache(@RequestParam("geocacheId") Long geocacheId){
        Geocache geocache = geocacheService.getGeocache(geocacheId);
        return geocache;
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
        if(geocacheService.createGeocacheEntry(geocache)==1) {
            return 1;
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "getNearestGeocache")
    @ResponseBody
    public Geocache getNearestGeocache(@RequestParam Map o){
        Geocache geocache = geocacheService.getNearestGeocache((Double)o.get("Latitudes"),(Double) o.get("Longitudes"));
        return geocache;
    }


}
