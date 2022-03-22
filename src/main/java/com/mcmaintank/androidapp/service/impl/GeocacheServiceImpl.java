package com.mcmaintank.androidapp.service.impl;

import com.mcmaintank.androidapp.mapper.GeocacheMapper;
import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.service.GeocacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-9 16:12
 */
@Service
public class GeocacheServiceImpl implements GeocacheService {

    @Autowired
    GeocacheMapper geocacheMapper;

    @Override
    public Geocache getGeocache(Long geocacheId) {
        return geocacheMapper.selectGeocacheById(geocacheId);
    }

    @Override
    public int createGeocacheEntry(Geocache geocache) {
        if(geocacheMapper.insertGeocache(geocache)==1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Geocache getGeocacheByUser(Long userId) {
        return geocacheMapper.selectGeocacheByUserId(userId);
    }

    @Override
    public Geocache getNearestGeocache(Double latitudes, Double longitudes) {
        return null;
    }


}
