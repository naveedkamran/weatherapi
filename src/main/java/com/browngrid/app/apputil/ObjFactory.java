package com.browngrid.app.apputil;

import com.browngrid.app.message.Receiver;
import com.browngrid.app.repository.WeatherRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Receiver receiver;

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

    /**
     * @return the rabbitTemplate
     */
    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    /**
     * @param rabbitTemplate the rabbitTemplate to set
     */
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @return the receiver
     */
    public Receiver getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }
}
