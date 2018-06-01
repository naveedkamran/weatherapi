package com.browngrid.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Naveed Kamran
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {

    private Long cod;
    private Long dt;
    private Long id;
    private String base;
    private String name;
    private String visibility;
    private GeoLocation coord;
//    private Weather weather[];
    private Main main;
    private Sys sys;

    public WeatherDetails() {
//        weather = new Weather[1];
    }

    @Override
    public String toString() {
        return "WeatherDetails{" + "cod=" + cod + ", dt=" + dt + ", id=" + id + ", base=" + base + ", name=" + name + ", visibility=" + visibility + ", coord=" + coord + ", main=" + main + ", sys=" + sys + '}';
    }

    /**
     * @return the cod
     */
    public Long getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(Long cod) {
        this.cod = cod;
    }

    /**
     * @return the dt
     */
    public Long getDt() {
        return dt;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the base
     */
    public String getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the coord
     */
    public GeoLocation getCoord() {
        return coord;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(GeoLocation coord) {
        this.coord = coord;
    }

    /**
     * @return the main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * @return the sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * @param sys the sys to set
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

}
