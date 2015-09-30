/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Apr 08, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.lab.cloud.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Component;

/**
 * Base Resource.
 */
@Component
public class BaseResource {
    
    @Context
    private HttpServletRequest request;
    
    //@HeaderParam("agencySapId") Long agencySapId;

}
