package com.browngrid.app.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Naveed Kamran
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {

    private Double lon;
    private Double lat;

    public GeoLocation() {
    }

    public GeoLocation(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "GeoLocation{" + "lon=" + lon + ", lat=" + lat + '}';
    }

    /**
     * @return the lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return the lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

}
