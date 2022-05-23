package com.mcmaintank.androidapp.service.impl;

import com.mcmaintank.androidapp.model.Geocache;
import com.mcmaintank.androidapp.service.GeocacheConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
@ComponentScan(value = "com.mcmaintank.androidapp.config")
public class GeocacheConsumerServiceImpl implements GeocacheConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    String tokenJsonString = "{\n" +
            "    \"access_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9wZmEuZm9yZWNhLmNvbVwvYXV0aG9yaXplXC90b2tlbiIsImlhdCI6MTY1MzIxMTYzMywiZXhwIjo5OTk5OTk5OTk5LCJuYmYiOjE2NTMyMTE2MzMsImp0aSI6IjRmOGMyMjFhN2E0MmJlNTUiLCJzdWIiOiJxaXllbGluMTk5OSIsImZtdCI6IlhEY09oakM0MCtBTGpsWVR0amJPaUE9PSJ9.FXIO9FcJTUxUlOSy9Ng6vT_DaR4J_Ji5NNDAUn2s7XM\",\n" +
            "    \"expires_in\": null,\n" +
            "    \"token_type\": \"bearer\"\n" +
            "}";
    String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9wZmEuZm9yZWNhLmNvbVwvYXV0aG9yaXplXC90b2tlbiIsImlhdCI6MTY1MzIxMTYzMywiZXhwIjo5OTk5OTk5OTk5LCJuYmYiOjE2NTMyMTE2MzMsImp0aSI6IjRmOGMyMjFhN2E0MmJlNTUiLCJzdWIiOiJxaXllbGluMTk5OSIsImZtdCI6IlhEY09oakM0MCtBTGpsWVR0amJPaUE9PSJ9.FXIO9FcJTUxUlOSy9Ng6vT_DaR4J_Ji5NNDAUn2s7XM";
    String GEOCACHE_TOKEN = "";



    @Override
    public Geocache parseGeocache(String url, String refCode) {
        String responseString = "";
        url = "https://www.geocaching.com/v1/geocaches/"+refCode;
        Geocache geocache = new Geocache();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("access_token",GEOCACHE_TOKEN);
        httpHeaders.add("fields","false");
        httpHeaders.add("expand","false");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        Object object = restTemplate.postForObject(url, httpEntity, Object.class);
        Map entity = (Map) object;
        String jsonStr = entity.get("postedCoordinates").toString();
        String s = jsonStr.replaceAll("[{}\"]", "");
        System.out.println(s);
        String[] strings = s.split(",");
        String[] latStr = strings[0].split(":");
        String[] lonStr = strings[1].split(":");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        geocache.setGeocacheLatitudes(Double.valueOf(String.valueOf(latStr)));
        geocache.setGeocacheLongitudes(Double.valueOf(String.valueOf(lonStr)));
        try {
            geocache.setGeocacheDateOfUpload(sf.parse(entity.get("placedDate").toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        geocache.setGeocacheLocationDescription(entity.get("hints").toString());
        return geocache;
    }

    @Override
    public String testParse(String url, String location) {
        String responseString = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token",TOKEN);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        responseString = restTemplate.getForEntity(url+location+"?token="+TOKEN, String.class).getBody();
        return responseString;
    }


}
