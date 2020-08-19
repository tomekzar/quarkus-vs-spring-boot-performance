package com.cogrammer.controller;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.Message;
import com.cogrammer.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService service;

    @GetMapping(path = "/api/hello")
    public Message hello() {
        return service.blockingHello();
    }

    @GetMapping(path = "/api/cities")
    public Cities cityByCountryCode(@RequestParam("country_code") final String countryCode) {
        return service.findCitiesByCountryCode(countryCode);
    }
}
