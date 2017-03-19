package com.leechi.vendorclient.amzMarket;


import com.amazon.mws.order.MarketplaceWebServiceOrdersClient;
import com.amazon.mws.order.MarketplaceWebServiceOrdersConfig;

public class AmzMarketOrderClientBuilder {
    private  String accessKey;
    private  String secretKey;
    private  String appName;
    private  String appVersion;
    private  String serviceURL= "https://mws.amazonservices.com/Orders/2013-09-01";
    ;


    public AmzMarketOrderClientBuilder setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }


    public AmzMarketOrderClientBuilder setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }


    public AmzMarketOrderClientBuilder setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public AmzMarketOrderClientBuilder setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }


    public AmzMarketOrderClientBuilder setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
        return this;
    }

    public MarketplaceWebServiceOrdersClient build(){
        MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
        config.setServiceURL(serviceURL);
        return new MarketplaceWebServiceOrdersClient(accessKey, secretKey,
                appName, appVersion, config);

    }
}
