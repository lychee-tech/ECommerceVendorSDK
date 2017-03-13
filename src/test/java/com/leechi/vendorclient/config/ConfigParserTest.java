package com.leechi.vendorclient.config;

import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingProperties;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigParserTest {
    private static  ConfigParser parser;

    @BeforeClass
    public static void setup(){
        parser = new ConfigParser();
        parser.loadResourcePropertyFile("app-config");
    }


    @Test
    public void testAssociateAccesskey(){
        String accessKey = parser.getValue(AmzAdvertisingProperties.accessKeyId);
        assertEquals("AKIAIML7O3PCES7EKM7Q", accessKey);
    }

    @Test
    public void testAssociateTag(){
        String tag = parser.getValue(AmzAdvertisingProperties.tag);
        assertEquals("handacc-20", tag);
    }

    @Test
    public void testAdvertisingUrl(){
        String url = parser.getValue(AmzAdvertisingProperties.endpint);
        assertEquals("https://webservices.amazon.com", url);
    }

    @Test
    public void testPropertyInEnvironment(){
        String secretKey = parser.getValue(AmzAdvertisingProperties.secret);
        assertNotNull(secretKey);
        assertFalse(secretKey.trim().equals(""));
    }
}
