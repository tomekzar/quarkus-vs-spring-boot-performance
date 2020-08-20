package com.cogrammer;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class ApplicationTest {

    @Test
    void should_expose_readiness_probe() {
        given()
            .when()
                .get("/api/health/ready")
            .then()
                .statusCode(200);
    }

    @Test
    void should_expose_liveness_probe() {
        given()
            .when()
                .get("/api/health/live")
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
}