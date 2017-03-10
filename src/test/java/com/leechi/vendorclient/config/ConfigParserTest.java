package com.leechi.vendorclient.config;


import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigParserTest {
    @Test
    public void testAssociateAccesskey(){
        String accessKey = ConfigParser.getValue("aws.associate.accesskeyId");
        assertEquals("AKIAIML7O3PCES7EKM7Q", accessKey);
    }

    @Test
    public void testAssociateTag(){
        String tag = ConfigParser.getValue("aws.associate.tag");
        assertEquals("handacc-20", tag);
    }

    @Test
    public void testAdvertisingUrl(){
        String url = ConfigParser.getValue("aws.advertising.endpoint");
        assertEquals("https://webservices.amazon.com", url);
    }

    @Test
    public void testPropertyInEnvironment(){
        String secretKey = ConfigParser.getValue("aws_associate_secret");
        assertNotNull(secretKey);
        assertFalse(secretKey.trim().equals(""));
    }
}
