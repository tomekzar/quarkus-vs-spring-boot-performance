package com.cogrammer.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "CITY")
@Getter
public class CityEntity extends PanacheEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COUNTRY_CODE", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "POPULATION", nullable = false)
    private Long population;

    public static List<CityEntity> findByCountryCode(final String countryCode) {
        return find("countryCode", countryCode).list();
    }
}
