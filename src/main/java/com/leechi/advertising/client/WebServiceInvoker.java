package com.leechi.advertising.client;

import com.amazon.advertising.jaxws.AWSECommerceServicePortType;

import javax.xml.ws.WebServiceException;

interface WebServiceInvoker<T> {
    T invoke(AWSECommerceServicePortType port) throws WebServiceException;
}
