package com.leechi.vendorclient.net;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import static  org.junit.Assert.*;

public class JerseyClientFactoryTest {
    @Test
    public void testGetHttpGoogle() throws  Exception {
        Client client = new JerseyClientBuilder().build();
        WebTarget webTarget= client.target("http://www.google.com");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_HTML);
        String response = invocationBuilder.get(String.class);

        assertNotNull(response);
        assertFalse(StringUtils.isBlank(response));
    }

    @Test
    public  void testHttpsAmazon() throws Exception {
        Client client = new  JerseyClientBuilder().build();
        WebTarget webTarget= client.target("https://www.amazon.com");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_HTML);
        String response = invocationBuilder.get(String.class);

        assertNotNull(response);
        assertFalse(StringUtils.isBlank(response));
    }
}
