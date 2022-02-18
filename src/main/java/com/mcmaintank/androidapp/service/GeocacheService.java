package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.Geocache;

import java.sql.Date;

/**
 * @author MCMainTank
 * @version 1.0
 * @date 2022-01-9 16:12
 */
public interface GeocacheService {

    public Geocache getGeocache(Long geocacheId);

    public int createGeocacheEntry(Geocache geocache);

    public Geocache getNearestGeocache(Double latitudes, Double longitudes);
}
