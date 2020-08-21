package com.cogrammer.service;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.City;
import com.cogrammer.dto.Message;
import com.cogrammer.entity.CityEntity;
import lombok.SneakyThrows;

import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class ExampleService {

    @SneakyThrows
    public Message blockingHello() {
        Thread.sleep(100);
        return new Message("Hello!");
    }

    public Cities findCitiesByCountryCode(final String countryCode) {
        var cities = CityEntity.findByCountryCode(countryCode).stream()
                .map(City::from)
                .collect(Collectors.toList());
        return new Cities(cities);
    }
}
