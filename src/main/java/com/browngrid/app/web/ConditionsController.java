package com.browngrid.app.web;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.sun.SunRiseSet;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConditionsController {

    @Autowired
    private ObjFactory objFactory;

    @GetMapping("/sunrise_sunset")
    @ResponseBody
    public String reload_weather(
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "longitude", required = true) Double longitude) {
        Calendar[] sunriseSunset = SunriseSunset.getSunriseSunset(Calendar.getInstance(), latitude, longitude);

        System.out.println("Sunrise at: " + sunriseSunset[0].getTime());
        System.out.println("Sunset at: " + sunriseSunset[1].getTime());

        return new Gson().toJson(new SunRiseSet(latitude, longitude, sunriseSunset[0].getTime(), sunriseSunset[1].getTime()));
    }

    @GetMapping("/check_condition")
    @ResponseBody
    public ResponseEntity weather(
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
