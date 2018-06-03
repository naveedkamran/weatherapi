package com.browngrid.app.repository;

import com.browngrid.app.domain.WeatherDetails;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<WeatherDetails, String> {

//    public WeatherDetails findByFirstName(Long id);
//
    public List<WeatherDetails> findAll();
}
