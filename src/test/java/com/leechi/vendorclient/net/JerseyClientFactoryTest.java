package com.leechi.vendorclient.net;


import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class JerseyClientFactoryTest {
    @Test
    public void testGetGoogle() throws  Exception {
        Client client = JerseyClientFactory.getClient();
        WebTarget webTarget= client.target("www.google.com");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_HTML);
        String response = invocationBuilder.get(String.class);

    }
}
