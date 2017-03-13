package com.leechi.vendorclient.amzAdvertising;


import com.amazon.advertising.jaxws.AWSECommerceService;
import com.amazon.advertising.jaxws.AWSECommerceServicePortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmzAdvertisingClient {
    private  AWSECommerceServicePortType port;

    private static final Logger logger = LoggerFactory
            .getLogger(AmzAdvertisingClient.class);

    private int retryCount = 3;
    private long retryInterval = 1000; // [msec]
    private static final Pattern HTTP_STATUS_PATTERN = Pattern
            .compile("status code ([0-9]{3})");


    private AmzAdvertisingClient(String awsAssociateSecret, String awsAdvertisingUrl) {
        AWSECommerceService service = new AWSECommerceService();
        service.setHandlerResolver(new AwsHandlerResolver(
                awsAssociateSecret));
        AWSECommerceServicePortType port = service
                .getAWSECommerceServicePort();
        ((BindingProvider) port)
                .getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                        awsAdvertisingUrl
                                + "/onca/soap?Service=AWSECommerceService");
        this.port = port;
    }


    public <T> T invoke(AmzAdvertisingInvoker<T> invoker){
        return invokeWithRetry(invoker);
    }


    private <T> T invokeWithRetry(AmzAdvertisingInvoker<T> invoker)
            throws WebServiceException {
        int retry = 0;
        T result = null;
        while (true) {
            try {
                result = invoker.invoke(this.port);
                break;
            } catch (WebServiceException e) {
                Matcher m = HTTP_STATUS_PATTERN.matcher(e.getMessage());
                if (m.find() && Integer.parseInt(m.group(1)) == 503) {
                    logger.warn("web service exception occurred", e);
                    if (retry < retryCount && retryInterval > 0) {
                        retry++;
                        try {
                            logger.debug("retry {}/{}", retry, retryCount);
                            TimeUnit.MILLISECONDS.sleep(retryInterval * retry);
                        } catch (InterruptedException ignored) {
                        }
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        }
        return result;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String associateSecret;
        private String awsAdvertisingUrl;


        public Builder setAwsAdvertisingUrl(String awsAdvertisingUrl) {
            this.awsAdvertisingUrl = awsAdvertisingUrl;
            return this;
        }


        public Builder setAssociateSecret(String associateSecret) {
            this.associateSecret = associateSecret;
            return this;
        }

        public AmzAdvertisingClient build () {
            return new AmzAdvertisingClient(this.associateSecret, this.awsAdvertisingUrl);
        }
    }


}
