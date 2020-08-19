package com.cogrammer.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
@Getter
public class CityEntity {

    @Id
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COUNTRY_CODE", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "POPULATION", nullable = false)
    private Long population;
}
