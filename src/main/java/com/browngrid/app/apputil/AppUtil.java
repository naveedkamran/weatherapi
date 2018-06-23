package com.browngrid.app.apputil;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.browngrid.app.domain.sun.SunRiseSet;
import com.browngrid.app.domain.weather.GeoLocation;
import com.browngrid.app.domain.weather.WeatherDetails;
import java.util.Calendar;
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
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + location.getLat()
                + "&lon=" + location.getLon() + "&appid=" + appId;
        System.out.println("Calling webservice " + url);
        return restTemplate.getForObject(url, WeatherDetails.class);
    }

    public SunRiseSet getSunriseSunset(Double lon, Double lat) {
        Calendar[] sunriseSunset = SunriseSunset.getSunriseSunset(Calendar.getInstance(), lat, lon);

        return new SunRiseSet(lon, lat, sunriseSunset[0].getTime(), sunriseSunset[1].getTime());
    }

}
