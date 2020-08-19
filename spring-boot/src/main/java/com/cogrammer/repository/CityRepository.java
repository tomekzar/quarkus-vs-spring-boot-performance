package com.cogrammer.repository;

import com.cogrammer.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

    List<CityEntity> findAllByCountryCode(final String countryCode);
}
