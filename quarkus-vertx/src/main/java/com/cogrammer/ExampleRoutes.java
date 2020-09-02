package com.cogrammer;

import com.cogrammer.dto.Cities;
import com.cogrammer.dto.Message;
import com.cogrammer.service.ExampleService;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RouteBase(path = "/api", produces = "application/json")
public class ExampleRoutes {

    private final ExampleService service;

    @Inject
    public ExampleRoutes(ExampleService service) {
        this.service = service;
    }

    @Route(path = "/hello", methods = HttpMethod.GET)
    public Uni<Message> hello() {
        return service.blockingHello()
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Route(path = "/cities", methods = HttpMethod.GET)
    public Uni<Cities> cityByCountryCode(final RoutingContext context) {
        var countryCode = context.queryParam("country_code").get(0);
        return service.findCitiesByCountryCode(countryCode);
    }
}