package com.browngrid.app.apputil;

import com.browngrid.app.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Naveed Kamran
 */
@Component
public class ObjFactory {

    @Autowired
    private AppUtil appUtil;
    @Autowired
    private WeatherRepository weatherRepository;

    /**
     * @return the appUtil
     */
    public AppUtil getAppUtil() {
        return appUtil;
    }

    /**
     * @param appUtil the appUtil to set
     */
    public void setAppUtil(AppUtil appUtil) {
        this.appUtil = appUtil;
    }

    /**
     * @return the weatherRepository
     */
    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }

    /**
     * @param weatherRepository the weatherRepository to set
     */
    public void setWeatherRepository(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }
}
