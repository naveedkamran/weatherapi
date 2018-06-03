package com.browngrid.app.task;

import com.browngrid.app.apputil.ObjFactory;
import com.browngrid.app.domain.GeoLocation;
import com.browngrid.app.domain.WeatherDetails;
import com.datenc.commons.date.DateUtil;
import java.util.Calendar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Naveed Kamran
 */
@Component
public class UpdateWeatherJob implements Runnable, ApplicationContextAware {

    ApplicationContext applicationContext = null;
    @Autowired
    private ObjFactory objFactory;

    //@Scheduled(fixedRate = 1000 * 60 * 1, initialDelay = 1000 * 60 * 1)
    @Scheduled(cron = "0 0/3 * * * ?")
    @Override
    public void run() {
        System.out.println(DateUtil.getInstance().getDateTimeAsString(Calendar.getInstance().getTime()) + " UpdateWeatherJob executing .....");
        try {
            WeatherDetails weatherDetails
                    = objFactory.getAppUtil().getWeather(new GeoLocation(13.4050, 52.5200));

            System.out.println(weatherDetails.toString());

            objFactory.getWeatherRepository().save(weatherDetails);
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
