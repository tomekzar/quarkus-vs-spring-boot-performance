package com.cogrammer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("CITY")
public class CityEntity {

    @Id
    private Long id;

    @Column("NAME")
    private String name;

    @Column("COUNTRY_CODE")
    private String countryCode;

    @Column("POPULATION")
    private Long population;
}
