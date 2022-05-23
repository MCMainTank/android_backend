package com.mcmaintank.androidapp.service.impl;

import com.mcmaintank.androidapp.mapper.GeocacheMapper;
import com.mcmaintank.androidapp.mapper.UserMapper;
import com.mcmaintank.androidapp.model.Activity;
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
    @Autowired
    UserMapper userMapper;

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
    public List<Geocache> getNearestGeocache(Double latitudes, Double longitudes) {
        return geocacheMapper.selectGeocacheByLocation(latitudes-0.05,latitudes+0.05,longitudes-0.05,longitudes+0.05);
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
    public void changeGeocacheById(Long geocacheId, String geocacheLocationDescription) {
        geocacheMapper.updateGeocacheById(geocacheId,geocacheLocationDescription);
    }

    @Override
    public int reportGeocache(Activity activity) {
        Integer reportId = 0;
        if(geocacheMapper.selectGeocacheById(activity.getGeocacheId())!=null){
            if(isReported(activity.getUserId(),activity.getGeocacheId())){
                geocacheMapper.updateGeocacheReportedById(activity.getGeocacheId());
                Long pid = geocacheMapper.selectGeocacheById(activity.getGeocacheId()).getPid();
                userMapper.updateUserReported(geocacheMapper.selectReportedSum(pid),pid);
                userMapper.insertActivity(activity);
                return 1;
            }else
                return 2;
        }else return 0;
    }

    @Override
    public int logicDeleteGeocache(Long geocacheId){
        return geocacheMapper.logicDeleteGeocache(geocacheId);
    }

    @Override
    public boolean isReported(Long geocacheId, Long userId) {
        if(userMapper.selectReportActivityId(userId,geocacheId)==0){
            return false;
        }else
            return true;
    }


}
