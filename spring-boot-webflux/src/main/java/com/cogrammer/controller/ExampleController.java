package com.cogrammer.controller;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.Message;
import com.cogrammer.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService service;

    @GetMapping(path = "/hello")
    public Mono<Message> hello() {
        return service.blockingHello();
    }

    @GetMapping(path = "/cities")
    public Mono<Cities> cityByCountryCode(@RequestParam("country_code") final String countryCode) {
        return service.findCitiesByCountryCode(countryCode);
    }
}
