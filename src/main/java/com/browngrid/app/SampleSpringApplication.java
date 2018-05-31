package com.browngrid.app;

import com.browngrid.app.domain.WeatherDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableScheduling
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SampleSpringApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleSpringApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();
        WeatherDetails quote = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?lat=52.5200&lon=13.4050&appid=cba2e64c1002d6ef325138a66104b790", WeatherDetails.class);
        System.out.println(quote.toString());
    }

}
