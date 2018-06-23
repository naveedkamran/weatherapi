package com.browngrid.app.web;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.sun.SunRiseSet;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Date;
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

    @RequestMapping( value = "/sunrise_sunset", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity sunrise_sunset(
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "longitude", required = true) Double longitude) {
        Calendar[] sunriseSunset = SunriseSunset.getSunriseSunset(Calendar.getInstance(), latitude, longitude);

        System.out.println("Sunrise at: " + sunriseSunset[0].getTime());
        System.out.println("Sunset at: " + sunriseSunset[1].getTime());

        return new ResponseEntity(new Gson().toJson(new SunRiseSet(latitude, longitude, sunriseSunset[0].getTime(), sunriseSunset[1].getTime())), HttpStatus.OK);
    }

    @RequestMapping(value = "/check_condition", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity check_condition(
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "longitude", required = true) Double longitude,
            @RequestParam(value = "conditions", required = true) String conditions) {
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
}
