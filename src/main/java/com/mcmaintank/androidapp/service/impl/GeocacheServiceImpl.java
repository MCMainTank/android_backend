package com.mcmaintank.androidapp.service.impl;

import com.mcmaintank.androidapp.mapper.GeocacheMapper;
import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.service.GeocacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Geocache> getGeocacheByUser(Long userId) {
        return geocacheMapper.selectGeocacheByUserId(userId);
    }

    @Override
    public int getLatestGeocacheIdByUser(Long userId) {
        return geocacheMapper.selectLatestGeocacheIdByUserId(userId);
    }

    @Override
    public Geocache getNearestGeocache(Double latitudes, Double longitudes) {
        return null;
    }

    @Override
    public int getDeleted(Long geocacheId){
        return geocacheMapper.getDeleted(geocacheId);
    }

    @Override
    public List<Geocache> getTopTenReportedGeocaches() {
        return geocacheMapper.selectTopTenReportedGeocaches();
    }

    @Override
    public int logicDeleteGeocache(Long geocacheId){
        return geocacheMapper.logicDeleteGeocache(geocacheId);
    }


}
