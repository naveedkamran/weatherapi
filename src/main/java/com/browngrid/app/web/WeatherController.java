package com.browngrid.app.web;

import com.browngrid.app.Constants;
import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.weather.GeoLocation;
import com.browngrid.app.domain.weather.WeatherDetails;
import com.datenc.commons.ui.AppMessage;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

    @Autowired
    private ObjFactory objFactory;
 
    @RequestMapping(value = "/weather/{lon}/{lat}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
 
    @ResponseBody
    public String weather(
            @PathVariable(value = "lon", required = true) Double lon,
            @PathVariable(value = "lat", required = true) Double lat) {
        WeatherDetails weatherDetails
                = objFactory.getAppUtil().getWeather(new GeoLocation(lon, lat));

        return new Gson().toJson(weatherDetails);
    }
 
    @RequestMapping(value = "/weather/{location}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String weather(
            @PathVariable(value = "location", required = true) String location) {
        WeatherDetails weatherDetails = objFactory.getAppUtil().getWeather(location);

        return new Gson().toJson(weatherDetails);
    }

    @RequestMapping(value = "/weather/{location}/check_condition/{condition}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String weather(
            @PathVariable(value = "location", required = true) String location,
            @PathVariable(value = "condition", required = true) String condition) {
        WeatherDetails weatherDetails = objFactory.getAppUtil().getWeather(location);

        return new Gson().toJson(weatherDetails);
    }

    @RequestMapping(value = "/reload_weather", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
 
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
}
