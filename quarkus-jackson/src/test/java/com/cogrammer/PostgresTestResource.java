package com.cogrammer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class PostgresTestResource implements QuarkusTestResourceLifecycleManager {

    private static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:12.3")
            .withDatabaseName("performance-benchmark")
            .withUsername("performance-benchmark")
            .withPassword("performance-benchmark")
            .withInitScript("database/init.sql")
            .withExposedPorts(5432);

    @Override
    public Map<String, String> start() {
        DATABASE.start();
        return Map.of(
                "quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl(),
                "quarkus.datasource.username", "performance-benchmark",
                "quarkus.datasource.password", "performance-benchmark"
        );
    }

    @Override
    public void stop() {
        DATABASE.stop();
    }
}
