package com.leechi.vendorclient.client.amzMarket;


import com.amazon.mws.orders.MarketplaceWebServiceOrdersClient;
import com.amazon.mws.orders.model.*;
import com.amazon.mws.shared.MwsUtl;
import com.leechi.vendorclient.amzMarket.AmzMarketOrderClientBuilder;
import com.leechi.vendorclient.config.ConfigParser;
import org.junit.BeforeClass;
import org.junit.Test;


import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;


public class AmzMarketOrderClientBuilderTest {
    private static ConfigParser parser= new ConfigParser();
    private static String mwsAccessKeyId,mwsSecrete, mwsSellerId, marketIds;
    private static List<String> marketIdList=null;

    @BeforeClass
    public static void setup(){
        parser.loadResourcePropertyFile("app-config");
        mwsAccessKeyId= parser.getValue("aws_mws_access");
        mwsSecrete = parser.getValue("aws_mws_secret");
        mwsSellerId=parser.getValue("aws_mws_sellerId");
        marketIds=parser.getValue("aws_mws_marketIds");
        marketIdList =Arrays.asList(marketIds.split(","));
    }

    @Test
    public void testGetOrders(){
        AmzMarketOrderClientBuilder builder = new AmzMarketOrderClientBuilder();
        builder.setAccessKey(mwsAccessKeyId);
        builder.setSecretKey(mwsSecrete);

        MarketplaceWebServiceOrdersClient client = builder.build();

        GetOrderRequest request = new GetOrderRequest();
        request.setSellerId(mwsSellerId);
        request.setAmazonOrderId(Arrays.asList("104-6918117-4981026"));

        GetOrderResponse response = client.getOrder(request);
    }


    @Test
    public void listOrders(){
        AmzMarketOrderClientBuilder builder = new AmzMarketOrderClientBuilder();
        builder.setAccessKey(mwsAccessKeyId);
        builder.setSecretKey(mwsSecrete);

        MarketplaceWebServiceOrdersClient client = builder.build();

        ListOrdersRequest request = new ListOrdersRequest();
        request.setSellerId(mwsSellerId);

        GregorianCalendar c = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        c.set(Calendar.DAY_OF_MONTH,1);

        XMLGregorianCalendar createAfter = MwsUtl.getDTF().newXMLGregorianCalendar(c);

        request.setCreatedAfter(createAfter);
        request.setMarketplaceId(marketIdList);

        ListOrdersResponse response = client.listOrders(request);

    }
}
