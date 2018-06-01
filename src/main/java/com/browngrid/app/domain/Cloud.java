package com.browngrid.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Naveed Kamran
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cloud {

    private Integer all;

    public Cloud(Integer all) {
        this.all = all;
    }

    /**
     * @return the all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * @param all the all to set
     */
    public void setAll(Integer all) {
        this.all = all;
    }

}
