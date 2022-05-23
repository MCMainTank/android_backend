package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.Geocache;

public interface GeocacheConsumerService {

    Geocache parseGeocache(String url, String refCode);

    String testParse(String url, String location);

}
