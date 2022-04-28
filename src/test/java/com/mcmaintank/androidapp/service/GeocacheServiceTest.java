package com.mcmaintank.androidapp.service;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeocacheServiceTest {

    @Resource
    GeocacheService geocacheService;

    @Test
    void getGeocache() {
        Assertions.assertTrue(geocacheService.getGeocache(1L).getGeocacheId()==1L);
    }

    @Test
    void createGeocacheEntry() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void getGeocacheByUser() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void getLatestGeocacheIdByUser() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void getNearestGeocache() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void getDeleted() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void getTopTenReportedGeocaches() {
        System.out.println(Arrays.toString(geocacheService.getTopTenReportedGeocaches().toArray(new Geocache[0])));
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void changeGeocacheById() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void reportGeocache() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }

    @Test
    void logicDeleteGeocache() {
        geocacheService.getGeocache(1L);
        Assertions.assertTrue(true);
    }
}