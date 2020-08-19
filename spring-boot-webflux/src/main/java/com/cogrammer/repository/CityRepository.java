package com.cogrammer.repository;

import com.cogrammer.entity.CityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CityRepository extends ReactiveCrudRepository<CityEntity, Long> {

    Flux<CityEntity> findAllByCountryCode(final String countryCode);
}
