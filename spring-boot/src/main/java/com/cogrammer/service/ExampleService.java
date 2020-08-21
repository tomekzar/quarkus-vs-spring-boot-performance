package com.cogrammer.service;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.City;
import com.cogrammer.dto.Message;
import com.cogrammer.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final CityRepository repository;

    @SneakyThrows
    public Message blockingHello() {
        Thread.sleep(100);
        return new Message("Hello!");
    }

    public Cities findCitiesByCountryCode(final String countryCode) {
        var cities = repository.findAllByCountryCode(countryCode).stream()
                .map(City::from)
                .collect(Collectors.toList());
        return new Cities(cities);
    }
}
