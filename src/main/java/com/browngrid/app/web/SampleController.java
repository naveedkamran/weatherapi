package com.browngrid.app.web;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.browngrid.app.Constants;
import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.sun.SunRiseSet;
import com.browngrid.app.domain.weather.GeoLocation;
import com.browngrid.app.domain.weather.WeatherDetails;
import com.datenc.commons.ui.AppMessage;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @Autowired
    private ObjFactory objFactory;

    @GetMapping("/")
    @ResponseBody
    public String baseLink() {
        return "BrownGrid Server";
    }

    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "Ping response from the controller.";
    }

    @GetMapping("/reload_weather")
    @ResponseBody
    public String reload_weather() {
        try {
            WeatherDetails weatherDetails
                    = objFactory.getAppUtil().getWeather(new GeoLocation(13.4050, 52.5200));

            System.out.println(weatherDetails.toString());

            objFactory.getWeatherRepository().save(weatherDetails);

            System.out.println("Sending message...");
            objFactory.getRabbitTemplate().convertAndSend(Constants.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ BrownGrid demo!");
            objFactory.getReceiver().getLatch().await(10000, TimeUnit.MILLISECONDS);

            return new Gson().toJson(new AppMessage(true));
        } catch (InterruptedException ex) {
            return new Gson().toJson(new AppMessage(false, ex.getMessage()));
        }
    }

    @GetMapping("/weather")
    @ResponseBody
    public String weather(
            @RequestParam(value = "latitude", required = true) Double latitude,
            @RequestParam(value = "longitude", required = true) Double longitude) {
        WeatherDetails weatherDetails
                = objFactory.getAppUtil().getWeather(new GeoLocation(longitude, latitude));

        return new Gson().toJson(weatherDetails);
    }

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
}