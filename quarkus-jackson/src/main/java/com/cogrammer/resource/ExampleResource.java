package com.cogrammer.resource;

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
    public Message hello() {
        return service.blockingHello();
    }

    @GET
    @Path("/cities")
    public Cities cityByCountryCode(@QueryParam("country_code") final String countryCode) {
        return service.findCitiesByCountryCode(countryCode);
    }
}