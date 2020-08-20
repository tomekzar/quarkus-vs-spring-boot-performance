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
@ContextConfiguration(initializers = SpringBootWebfluxApplicationTests.PostgresInitializer.class)
@Testcontainers
class SpringBootWebfluxApplicationTests {

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
                    "spring.r2dbc.url=" + formatToReactiveUrl(DATABASE.getJdbcUrl()),
                    "spring.r2dbc.username=" + DATABASE.getUsername(),
                    "spring.r2dbc.password=" + DATABASE.getPassword(),
                    "spring.r2dbc.properties.sslMode=DISABLE"
            ).applyTo(context.getEnvironment());
        }

        private String formatToReactiveUrl(String jdbcUrl) {
            return jdbcUrl.replace("jdbc", "r2dbc");
        }
    }
}
