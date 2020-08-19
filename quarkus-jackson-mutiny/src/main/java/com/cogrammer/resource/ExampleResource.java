package com.cogrammer.resource;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import com.cogrammer.dto.Cities;
import com.cogrammer.dto.Message;
import com.cogrammer.service.ExampleService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

    private final ExampleService service;

    @Inject
    public ExampleResource(final ExampleService service) {
        this.service = service;
    }

    @GET
    @Path("/hello")
    public Uni<Message> hello() {
        return service.blockingHello()
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @GET
    @Path("/cities")
    public Uni<Cities> cityByCountryCode(@QueryParam("country_code") final String countryCode) {
        return service.findCitiesByCountryCode(countryCode);
    }
}