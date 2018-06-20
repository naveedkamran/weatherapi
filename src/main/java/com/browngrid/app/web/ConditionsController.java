package com.browngrid.app.web;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.browngrid.app.apputil.ObjFactory;
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
