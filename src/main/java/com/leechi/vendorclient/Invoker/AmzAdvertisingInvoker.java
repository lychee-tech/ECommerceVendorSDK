package com.leechi.vendorclient.Invoker;

import com.amazon.advertising.jaxws.AWSECommerceServicePortType;

import javax.xml.ws.WebServiceException;

public interface AmzAdvertisingInvoker<T> {
    T invoke(AWSECommerceServicePortType port) throws WebServiceException;
}
