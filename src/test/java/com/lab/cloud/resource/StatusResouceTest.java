/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Apr 29, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.lab.cloud.resource;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

/**
 * @author jcordones13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class StatusResouceTest {
    private static final Logger LOG = LoggerFactory.getLogger(StatusResouceTest.class);
    private Server tomcat;
    AsyncHttpClient httpClient = null;
    Future<Response> req;
    Response res;

    @Before
    public final void setUp() throws Exception {
        tomcat = new Server();
        tomcat.start();
        LOG.info("************************** TOMCAT PORT: " + tomcat.getPort());
    }

    @After
    public final void tearDown() throws Exception {
        this.tomcat.stop();
    }

    @Test
    public void test() {

        try {
            httpClient = new AsyncHttpClient();

            res = httpClient.prepareGet("http://localhost:" + tomcat.getPort() + "/lab/status/ping").execute().get();

            String statusResp = res.getResponseBody();
            LOG.debug("************************** STATUS ENDPOINT RESPONSE: " + statusResp);
            
            LOG.info("************************** THREAD GOING TO SLEEP");
            Thread.sleep(40000);
            LOG.info("************************** THREAD WAKING UP");

            Assert.assertNotNull(res.getResponseBody());
            Assert.assertEquals("pong", statusResp);

        } catch (InterruptedException | ExecutionException | IOException e) {
            LOG.error("**** STATUS REQUEST EXECUTION ERROR: " + e.getMessage());
            e.printStackTrace();

        } finally {
            if (httpClient != null)
                httpClient.close();
        }
    }
}
