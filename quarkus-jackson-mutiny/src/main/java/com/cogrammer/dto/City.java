package com.cogrammer.dto;

import lombok.Data;

@Data
public class City {

    private final Long id;
    private final String name;
    private final String countryCode;
    private final Long population;
}
