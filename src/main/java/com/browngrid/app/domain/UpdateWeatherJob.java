package com.browngrid.app.domain;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Naveed Kamran
 */
@Component
public class UpdateWeatherJob implements Runnable, ApplicationContextAware {

    ApplicationContext applicationContext = null;

    @Scheduled(fixedRate = 1000 * 60 * 1, initialDelay = 1000 * 60 * 1)
    @Scheduled(cron = "0 0/3 * * * ?")
    @Override
    public void run() {
        System.out.println("UpdateWeatherJob executing .....");
        try {
            RestTemplate restTemplate = new RestTemplate();
            WeatherDetails quote = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?lat=52.5200&lon=13.4050&appid=cba2e64c1002d6ef325138a66104b790", WeatherDetails.class);
            System.out.println(quote.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("UpdateWeatherJob execution completed");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

}
