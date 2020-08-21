package com.cogrammer.service;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.City;
import com.cogrammer.dto.Message;
import com.cogrammer.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final CityRepository repository;

    public Mono<Message> blockingHello() {
        return Mono.fromSupplier(() -> {
            try {
                Thread.sleep(100);
                return new Message("Hello!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Mono<Cities> findCitiesByCountryCode(final String countryCode) {
        return repository.findAllByCountryCode(countryCode)
                .map(City::from)
                .collectList()
                .map(Cities::new);
    }
}
