package com.leechi.vendorclient.net;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class JerseyClientBuilder {
    private int jerseyClient_Read_Timeout=1000*60*3;       //3 minutes
    private int jerseyClient_Connect_Timeout =1000*60*1;   //1 minute
    private int connectionPool_max=2000;
    private int connectionPool_maxPerRoute=40;
    private int idleConnection_Detect_Timeout= 1000*5;     //5 seconds


    public  Client build() throws NoSuchAlgorithmException, KeyManagementException{
        ClientConfig clientConfig = new ClientConfig();
        // values are in milliseconds
        clientConfig.property(ClientProperties.READ_TIMEOUT, jerseyClient_Read_Timeout);
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, jerseyClient_Connect_Timeout);


        Registry<ConnectionSocketFactory> socketFactoryRegistry =  RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();

        //connection manager
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(connectionPool_max);
        connectionManager.setDefaultMaxPerRoute(connectionPool_maxPerRoute);
        clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        clientConfig.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newBuilder().withConfig(clientConfig).sslContext(SslConfigurator.getDefaultContext()).build();

        IdleConnectionMonitorThread t = new IdleConnectionMonitorThread(idleConnection_Detect_Timeout, connectionManager);
        t.start();
        return client;
    }


    public void setJerseyClient_Read_Timeout(int jerseyClient_Read_Timeout) {
        this.jerseyClient_Read_Timeout = jerseyClient_Read_Timeout;
    }


    public void setJerseyClient_Connect_Timeout(int jerseyClient_Connect_Timeout) {
        this.jerseyClient_Connect_Timeout = jerseyClient_Connect_Timeout;
    }


    public void setConnectionPool_max(int connectionPool_max) {
        this.connectionPool_max = connectionPool_max;
    }


    public void setConnectionPool_maxPerRoute(int connectionPool_maxPerRoute) {
        this.connectionPool_maxPerRoute = connectionPool_maxPerRoute;
    }


    public void setIdleConnection_Detect_Timeout(int idleConnection_Detect_Timeout) {
        this.idleConnection_Detect_Timeout = idleConnection_Detect_Timeout;
    }
}
