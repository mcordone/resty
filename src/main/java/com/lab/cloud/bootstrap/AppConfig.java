/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Sep 29, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.lab.cloud.bootstrap;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author jcordones13
 *
 */

@ApplicationPath("lab")
public class AppConfig extends ResourceConfig {
    
    public AppConfig(){
        //logger.info("\n****************************** JERSEY CONFIGURING ******************************\n");
        register(RequestContextFilter.class);
        packages("com.lab.cloud");
        register(LoggingFilter.class);
    }
}
