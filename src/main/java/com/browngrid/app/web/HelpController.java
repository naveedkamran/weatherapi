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

    public String check_condition_sun(GeoLocation gl, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/check_condition/sun?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude() + "&conditions=isday", "Check if its day at " + urlTitle));
        help.append("<br/>").append(getLink("/check_condition/sun?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude() + "&conditions=isnight", "Check if its night at " + urlTitle));

        return help.toString();
    }

    public String check_condition_weather(GeoLocation gl, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/check_condition/weather?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude() + "&conditions=isday", "Check if its day at " + urlTitle));
        help.append("<br/>").append(getLink("/check_condition/weather?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude() + "&conditions=isnight", "Check if its night at " + urlTitle));

        return help.toString();
    }

    public String weather(GeoLocation gl, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/weather?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude(), "Weather in " + urlTitle));

        return help.toString();
    }

    @RequestMapping(name = "/help")
    @ResponseBody
    public String help() {
        //Country	Pakistan Latitude	33.738045 Longitude	73.084488
        GeoLocation glIslamabad = new GeoLocation(73.0479, 33.6844);
        GeoLocation glBerlin = new GeoLocation(52.5200, 13.4050);

        StringBuilder help = new StringBuilder();
        help.append(check_condition_sun(glIslamabad, "Islamabad"));
        help.append("<br/>");
        help.append(check_condition_sun(glBerlin, "Berlin"));
        help.append("<br/>");
        help.append("<br/>");
        help.append(check_condition_weather(glIslamabad, "Islamabad"));
        help.append("<br/>");
        help.append(check_condition_weather(glBerlin, "Berlin"));
        help.append("<br/>");
        help.append("<br/>");
        help.append(weather(glIslamabad, "Islamabad"));
        help.append("<br/>");
        help.append(weather(glBerlin, "Berlin"));

        return help.toString();
    }
}
