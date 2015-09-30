/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: ${MONTH_NAME_SHORT} ${DAY}, ${YEAR}
 *
 * Copyright ${YEAR} AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.lab.cloud.resource;

import java.io.File;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jcordones13
 */

public class Server {
    private static final Logger LOG = LoggerFactory.getLogger(Server.class);
    private Tomcat tomcat;
    private Context ctx;
    private int port;
    private String appBase;
    private String contextPath;
    private String hostname;
    private String baseDir;

    public Server() {
        this.port = 0;
        this.appBase = "src/main/webapp";
        this.contextPath = "";
        this.baseDir = ".";
        this.hostname = "localhost";
        this.tomcat = new Tomcat();
        this.tomcat.setPort(this.port);
        this.tomcat.setBaseDir(this.baseDir);
        this.tomcat.getHost().setAppBase(this.appBase);
        this.tomcat.setHostname(this.hostname);
        
        try {
            ctx = tomcat.addWebapp(contextPath, new File(appBase).getAbsolutePath());
        } catch (ServletException | NullPointerException e) {
            LOG.error("**** TOMCAT INITIALIZATION ERROR " + e.getMessage());
            e.printStackTrace();
        }
        
        File additionWebInfClasses = new File("build/classes");
        StandardRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
        
        //TODO check on this
        TomcatURLStreamHandlerFactory.disable();
        
        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);
    }

    public final void start() throws Exception {
        this.tomcat.start();
    }

    public final void stop() throws Exception {
        this.tomcat.stop();
    }

    public final int getPort() {
        return tomcat.getConnector().getLocalPort();
    }
}
