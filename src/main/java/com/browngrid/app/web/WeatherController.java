package com.browngrid.app.web;

import com.browngrid.app.apputil.JsonUtil;
import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.weather.GeoLocation;
import com.browngrid.app.domain.weather.WeatherDetails;
import com.google.gson.Gson;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

    @Autowired
    private ObjFactory objFactory;

    @RequestMapping(value = "/weather/{location}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String weather(
            @PathVariable(value = "location", required = true) String location) {
        WeatherDetails weatherDetails = objFactory.getAppUtil().getWeather(location);

        return new Gson().toJson(weatherDetails);
    }

    @RequestMapping(value = "/weather/{lon}/{lat}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String weather(
            @PathVariable(value = "lon", required = true) Double lon,
            @PathVariable(value = "lat", required = true) Double lat) {
        WeatherDetails weatherDetails
                = objFactory.getAppUtil().getWeather(new GeoLocation(lon, lat));

        return new Gson().toJson(weatherDetails);
    }

    @RequestMapping(value = "/weather/{location}/condition/{conditions}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity check_condition_weather(
            @PathVariable(name = "location", required = true) String location,
            @PathVariable(name = "conditions", required = true) String conditions) {
        WeatherDetails weatherDetails = null;
        try {
            weatherDetails = objFactory.getAppUtil().getWeather(location);
            String all_json = new Gson().toJson(weatherDetails);

            Map<String, Object> conditionsMap = JsonUtil.getInstance().getConditions(null, all_json);

            return new ResponseEntity(objFactory.getConditionUtil().checkCondition(conditionsMap, conditions), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param lat
     * @param lon
     * @param conditions
     * @return
     */
    @RequestMapping(value = "/weather/{lon}/{lat}/condition/{conditions}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> check_condition_weather(
            @PathVariable(name = "lon", required = true) Double lon,
            @PathVariable(name = "lat", required = true) Double lat,
            @PathVariable(name = "conditions", required = true) String conditions) {
        WeatherDetails weatherDetails = null;

        StringBuilder output = new StringBuilder();

        try {
            GeoLocation location = new GeoLocation(lon, lat);

            weatherDetails = objFactory.getAppUtil().getWeather(location);
            String all_json = new Gson().toJson(weatherDetails);

            Map<String, Object> conditionsMap = JsonUtil.getInstance().getConditions(null, all_json);

            return new ResponseEntity(objFactory.getConditionUtil().checkCondition(conditionsMap, conditions), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }

    }

//    @RequestMapping(value = "/reload_weather", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public String reload_weather() {
//        try {
//            WeatherDetails weatherDetails
//                    = objFactory.getAppUtil().getWeather(new GeoLocation(13.4050, 52.5200));
//
//            System.out.println(weatherDetails.toString());
//
//            objFactory.getWeatherRepository().save(weatherDetails);
//
//            System.out.println("Sending message...");
//            objFactory.getRabbitTemplate().convertAndSend(Constants.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ BrownGrid demo!");
//            objFactory.getReceiver().getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//            return new Gson().toJson(new AppMessage(true));
//        } catch (InterruptedException ex) {
//            return new Gson().toJson(new AppMessage(false, ex.getMessage()));
//        }
//    }
}
