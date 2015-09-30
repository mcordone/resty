/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Sept 15, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.lab.cloud.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

/**
 * @author jcordones13
 */
@Component
@Path("status")
public class StatusResource extends BaseResource {
    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }
}
