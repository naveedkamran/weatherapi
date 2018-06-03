package com.browngrid.app.apputil;

import com.browngrid.app.domain.GeoLocation;
import com.browngrid.app.domain.WeatherDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Naveed Kamran
 */
@Component
public class AppUtil {

    String appId = "cba2e64c1002d6ef325138a66104b790";

    public WeatherDetails getWeather(GeoLocation location) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "http://api.openweathermap.org/data/2.5/weather?lat=" + location.getLat()
                + "&lon=" + location.getLon() + "&appid=" + appId, WeatherDetails.class);
    }
}
