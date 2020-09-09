package com.cogrammer.service;

import java.time.Duration;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.City;
import com.cogrammer.dto.Message;
import com.cogrammer.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

	private final CityRepository repository;

	public Mono<Message> delayedHello() {
		return Mono.just(new Message("Hello!"))
				.delayElement(Duration.ofMillis(100));
	}

	public Mono<Cities> findCitiesByCountryCode(final String countryCode) {
		return repository.findAllByCountryCode(countryCode)
				.map(City::from)
				.collectList()
				.map(Cities::new);
	}
}
