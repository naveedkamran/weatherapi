package com.browngrid.app.web;

import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.weather.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelpController {

    @Autowired
    private ObjFactory objFactory;
    String baseUrl = "http://localhost:8080";

    private String getLink(String url, String text) {
        return "<a href=\"" + baseUrl + url + "\">" + text + "</a>";
    }

    public String getGeoLocation(GeoLocation gl, String urlTitle) {
        StringBuilder help = new StringBuilder();

        help.append("<br/>").append(getLink("/check_condition?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude() + "&conditions=isday", "Check if its day at " + urlTitle));
        help.append("<br/>").append(getLink("/check_condition?latitude=" + gl.getLatitude() + "&longitude=" + gl.getLongitude() + "&conditions=isnight", "Check if its night at " + urlTitle));

        return help.toString();
    }

    @GetMapping("/help")
    @ResponseBody
    public String help() {
        //Country	Pakistan Latitude	33.738045 Longitude	73.084488
        GeoLocation glIslamabad = new GeoLocation(73.0479, 33.6844);
        GeoLocation glBerlin = new GeoLocation(52.5200, 13.4050);

        StringBuilder help = new StringBuilder();
        help.append(getGeoLocation(glIslamabad, "Islamabad"));
        help.append("<br/>");
        help.append(getGeoLocation(glBerlin, "Berlin"));

        return help.toString();
    }
}
