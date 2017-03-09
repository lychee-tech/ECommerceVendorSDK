package com.leechi.advertising.config;


import org.junit.Test;
import static org.junit.Assert.*;

public class AwsConfigTest {
    @Test
    public void testPropertyInFile(){
        String accessKey = AwsConfig.getValue("aws.accesskey.id");
        assertEquals("AKIAIML7O3PCES7EKM7Q", accessKey);
    }

    @Test
    public void testPropertyInEnvironment(){
        String secretKey = AwsConfig.getValue("aws_secret_accesskey");
        assertNotNull(secretKey);
        assertFalse(secretKey.trim().equals(""));
    }
}
