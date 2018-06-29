package com.browngrid.app.web;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.browngrid.app.apputil.JsonUtil;
import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.sun.SunRiseSet;
import com.browngrid.app.domain.weather.GeoLocation;
import com.browngrid.app.domain.weather.WeatherDetails;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConditionsController {

    @Autowired
    private ObjFactory objFactory;

    @RequestMapping(value = "/sunrise_sunset", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity sunrise_sunset(
            @RequestParam(name = "latitude", required = true) Double latitude,
            @RequestParam(name = "longitude", required = true) Double longitude) {
        Calendar[] sunriseSunset = SunriseSunset.getSunriseSunset(Calendar.getInstance(), latitude, longitude);

        System.out.println("Sunrise at: " + sunriseSunset[0].getTime());
        System.out.println("Sunset at: " + sunriseSunset[1].getTime());

        return new ResponseEntity(new Gson().toJson(new SunRiseSet(latitude, longitude, sunriseSunset[0].getTime(), sunriseSunset[1].getTime())), HttpStatus.OK);
    }

    @RequestMapping(value = "/check_condition/sun", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity check_condition_sun(
            @RequestParam(name = "latitude", required = true) Double latitude,
            @RequestParam(name = "longitude", required = true) Double longitude,
            @RequestParam(name = "conditions", required = true) String conditions) {
        Calendar[] sunriseSunset = SunriseSunset.getSunriseSunset(Calendar.getInstance(), latitude, longitude);

        if (sunriseSunset == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Date sunrise = sunriseSunset[0].getTime();
        Date sunset = sunriseSunset[1].getTime();

        Date now = Calendar.getInstance().getTime();

        boolean result = false;

        if (conditions.equals("isday")) {
            result = isDay(sunrise, sunset, now);
        } else {
            result = !isDay(sunrise, sunset, now);
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    private boolean isDay(Date sunrise, Date sunset, Date now) {
        if (now.before(sunset) && now.after(sunrise)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param conditions
     * @return
     */
    @RequestMapping(value = "/check_condition/weather", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity check_condition_weather(
            @RequestParam(name = "latitude", required = true) Double latitude,
            @RequestParam(name = "longitude", required = true) Double longitude,
            @RequestParam(name = "conditions", required = true) String conditions) {
        return null;
    }

    @RequestMapping(value = "/check_condition/weather_city", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity check_condition_weather(
            @RequestParam(name = "location", required = true) String location,
            @RequestParam(name = "conditions", required = true) String conditions) {
        WeatherDetails weatherDetails = null;
        try {
            //weatherDetails = objFactory.getAppUtil().getWeather(new GeoLocation(location));

            if (objFactory.getAppUtil().checkCondition(weatherDetails, conditions)) {
                return new ResponseEntity("Executed", HttpStatus.OK);
            } else {
                return new ResponseEntity("Executed", HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/check_condition/test", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    public String check_condition_weather() {
        WeatherDetails weatherDetails = null;

        StringBuilder output = new StringBuilder();

        try {
            GeoLocation glIslamabad = new GeoLocation(73.0479, 33.6844);
            GeoLocation glBerlin = new GeoLocation(52.5200, 13.4050);

            weatherDetails = objFactory.getAppUtil().getWeather(glBerlin);
            //String all_json = new Gson().toJson(weatherDetails);
            String all_json = "{\"cod\":200,\"dt\":1530129600,\"id\":1176615,\"base\":\"stations\",\"name\":\"Islamabad\",\"visibility\":\"7000\",\"coord\":{},\"main\":{\"temp\":302,\"pressure\":1004,\"humidity\":58,\"temp_min\":302,\"temp_max\":302},\"sys\":{\"type\":1,\"id\":7146,\"message\":0,\"country\":\"PK\",\"sunrise\":1530057581,\"sunset\":1530109333}}";

            Map<String, Object> conditionsMap = JsonUtil.getInstance().getConditions(null, all_json);
            System.out.println("");
            System.out.println("Values Map First way ==========================================================");

            for (String key : conditionsMap.keySet()) {
                String data = key + "=" + conditionsMap.get(key);
                System.out.println(data);

                output.append(data).append("\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return output.toString();
    }

}
