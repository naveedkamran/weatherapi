package com.browngrid.app.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Naveed Kamran
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {

    private Double longitude;
    private Double latitude;

    public GeoLocation() {
    }

    public GeoLocation(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "GeoLocation{" + "lon=" + getLongitude() + ", lat=" + getLatitude() + '}';
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
