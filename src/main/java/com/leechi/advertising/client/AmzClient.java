package com.leechi.advertising.client;


import com.amazon.advertising.jaxws.AWSECommerceService;
import com.amazon.advertising.jaxws.AWSECommerceServicePortType;
import com.leechi.advertising.config.AppConfig;
import com.leechi.advertising.handler.AwsHandlerResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmzClient {
    private  AWSECommerceServicePortType port;
    private AppConfig config = AppConfig.singleton();

    private static final Logger logger = LoggerFactory
            .getLogger(AmzClient.class);

    private int retryCount = 3;
    private long retryInterval = 1000; // [msec]
    private static final Pattern HTTP_STATUS_PATTERN = Pattern
            .compile("status code ([0-9]{3})");

    public AmzClient() {
        AWSECommerceService service = new AWSECommerceService();
        service.setHandlerResolver(new AwsHandlerResolver(
                config.getSecretAccessKey()));
        AWSECommerceServicePortType port = service
                .getAWSECommerceServicePort();
        ((BindingProvider) port)
                .getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                        config.getEndpoint()
                                + "/onca/soap?Service=AWSECommerceService");
        this.port = port;
    }


    public <T> T invoke(WebServiceInvoker<T> invoker){
        return invokeWithRetry(invoker);
    }


    private <T> T invokeWithRetry(WebServiceInvoker<T> invoker)
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


}
