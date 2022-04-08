package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.Geocache;

import java.sql.Date;
import java.util.List;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-9 16:12
 */
public interface GeocacheService {

    public Geocache getGeocache(Long geocacheId);

    public int createGeocacheEntry(Geocache geocache);

    public List<Geocache> getGeocacheByUser(Long userId);

    int getLatestGeocacheIdByUser(Long userId);

    public Geocache getNearestGeocache(Double latitudes, Double longitudes);

    int getDeleted(Long geocacheId);

    public List<Geocache> getTopTenReportedGeocaches();

    public void changeGeocacheById(Long geocacheId, String geocacheLocationDescription);

    int reportGeocache(Long geocacheId);

    int logicDeleteGeocache(Long geocacheId);
}
