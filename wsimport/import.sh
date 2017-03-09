#!/bin/sh
mkdir -p ../target/classes
wsimport -d ../target/classes -s ../src/main/java -p com.amazon.advertising.jaxws http://webservices.amazon.com/AWSECommerceService/AWSECommerceService.wsdl -b custom.xml .