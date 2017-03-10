package com.leechi.vendorclient.client;


public  class ClientFactory {
    private ClientFactory(){}

    public static AmzAdvertisingClient getAwsAdvertisingClient(){
        return new AmzAdvertisingClient();
    }
}
