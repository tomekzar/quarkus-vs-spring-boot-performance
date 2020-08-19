package com.cogrammer.dto;

import com.cogrammer.entity.CityEntity;
import lombok.Data;

@Data
public class City {

    private final Long id;
    private final String name;
    private final String countryCode;
    private final Long population;

    public static City from(final CityEntity entity) {
        return new City(entity.id, entity.getName(), entity.getCountryCode(), entity.getPopulation());
    }
}
