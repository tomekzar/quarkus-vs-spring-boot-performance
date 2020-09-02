package com.cogrammer.service;


import com.cogrammer.dto.Cities;
import com.cogrammer.dto.City;
import com.cogrammer.dto.Message;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.StreamSupport;

@Singleton
public class ExampleService {

    private final PgPool pgPool;

    @Inject
    public ExampleService(PgPool pgPool) {
        this.pgPool = pgPool;
    }

    public Uni<Message> blockingHello() {
        return Uni.createFrom().item(() -> {
            try {
                Thread.sleep(100);
                return new Message("Hello!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Uni<Cities> findCitiesByCountryCode(final String countryCode) {
        return pgPool.preparedQuery("SELECT id, name, country_code, population FROM city WHERE country_code = $1")
                .execute(Tuple.of(countryCode))
                .onItem().transformToMulti(this::rowSetToRows)
                .onItem().transform(this::toCityDto)
                .collectItems().asList()
                .onItem().transform(Cities::new);
    }

    private Multi<Row> rowSetToRows(io.vertx.mutiny.sqlclient.RowSet<Row> rs) {
        var rows = StreamSupport.stream(rs.spliterator(), false);
        return Multi.createFrom().items(rows);
    }

    private City toCityDto(Row r) {
        var id = r.getLong("id");
        var name = r.getString("name");
        var code = r.getString("country_code");
        var population = r.getLong("population");
        return new City(id, name, code, population);
    }
}
