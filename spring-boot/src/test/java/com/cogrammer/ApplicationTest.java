package com.cogrammer;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = ApplicationTest.PostgresInitializer.class)
@Testcontainers
class ApplicationTest {

    @Container
    private static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:12.3")
            .withDatabaseName("performance-benchmark")
            .withUsername("performance-benchmark")
            .withPassword("performance-benchmark")
            .withInitScript("database/init.sql")
            .withExposedPorts(5432);

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void should_expose_readiness_probe() {
        given()
            .when()
                .get("/api/health/ready")
            .then()
                .statusCode(200);
    }

    @Test
    void should_return_hello_message() {
        given()
            .when()
                .get("/api/hello")
            .then()
                .statusCode(200)
                .body("message", is("Hello!"));
    }

    @Test
    void should_return_cities_by_country_code() {
        given()
            .when()
                .queryParam("country_code", "NLD")
                .get("/api/cities")
            .then()
                .statusCode(200)
                .body("cities", hasSize(2));
    }

    static class PostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + DATABASE.getJdbcUrl(),
                    "spring.datasource.username=" + DATABASE.getUsername(),
                    "spring.datasource.password=" + DATABASE.getPassword()
            ).applyTo(context.getEnvironment());
        }
    }
}
