package com.browngrid.app.web;

import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.weather.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelpController {

    @Autowired
    private ObjFactory objFactory;
    String baseUrl = "http://localhost:8080";

    private String getLink(String url, String text) {
        return "<a href=\"" + baseUrl + url + "\">" + text + "</a>";
    }

    private String getExternalLink(String url, String text) {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }

    public String check_condition(GeoLocation gl, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/check_condition/" + gl.getLat() + "/" + gl.getLon() + "/isday", "Check if its day at " + urlTitle));
        help.append("<br/>").append(getLink("/check_condition/" + gl.getLat() + "/" + gl.getLon() + "/isnight", "Check if its night at " + urlTitle));

        return help.toString();
    }

    public String weather(GeoLocation gl, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/weather/" + gl.getLon() + "/" + gl.getLat(), "Weather in " + urlTitle));

        return help.toString();
    }

    public String weather(String location, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/weather/" + location, "Weather in " + urlTitle + " using location "));

        return help.toString();
    }

    @RequestMapping(value = "/help")
    @ResponseBody
    public String help() {
        //Country	Pakistan Lat	33.738045 Long	73.084488
        GeoLocation glIslamabad = new GeoLocation(73.0479, 33.6844);
        GeoLocation glBerlin = new GeoLocation(52.5200, 13.4050);

        StringBuilder help = new StringBuilder();
        help.append(weather(glIslamabad, "Islamabad"));
        help.append("<br/>");
        help.append(weather(glBerlin, "Berlin"));
        help.append("<br/>");
        help.append(weather("islamabad", "Islamabad"));
        help.append("<br/>");
        help.append(weather("berlin", "Berlin"));
        help.append("<br/>");
        help.append(weather("munich", "Munich"));
        help.append("<br/>");
        help.append("<br/>");

        help.append(getExternalLink("http://api.openweathermap.org/data/2.5/weather?lat=52.52&lon=13.405&appid=cba2e64c1002d6ef325138a66104b790", "From OpenWeather API"));
        help.append("<br/>");
        help.append(getExternalLink("http://localhost:8080/weather/13.405/52.52", "From our API"));
        help.append("<br/>");
        help.append("<br/>");
        help.append(check_condition(glIslamabad, "Islamabad"));
        help.append("<br/>");
        help.append(check_condition(glBerlin, "Berlin"));

        return help.toString();
    }
}
