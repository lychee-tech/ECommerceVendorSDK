package com.leechi.vendorclient.client;

import com.amazon.advertising.jaxws.*;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingInvoker;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingClient;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingProperties;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingQueryPreparer;
import com.leechi.vendorclient.config.ConfigParser;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.xml.ws.WebServiceException;
import java.math.BigInteger;


public class AmzAdvertisingClientTest {
    private static  AmzAdvertisingClient amzClient;
    private static  AmzAdvertisingQueryPreparer preparer;

    @BeforeClass
    public static  void setup(){
        ConfigParser parser= new ConfigParser();
        parser.loadResourcePropertyFile("app-config");
        String endPoint = parser.getValue(AmzAdvertisingProperties.endpint);
        String secret = parser.getValue(AmzAdvertisingProperties.secret);
        String accessKey = parser.getValue(AmzAdvertisingProperties.accessKeyId);
        String tag = parser.getValue(AmzAdvertisingProperties.tag);

        amzClient = AmzAdvertisingClient.builder().setAssociateSecret(secret).setAwsAdvertisingUrl(endPoint).build();
        preparer = AmzAdvertisingQueryPreparer.builder().setAwsAssociateAccessKey(accessKey).setAwsAssociateTag(tag).build();
    }

    @Test
    public void testItemSearch() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setItemPage(new BigInteger("1"));
        request.setSearchIndex("Books");
        request.setKeywords("Java");

        final ItemSearch itemSearch = preparer.prepareItemSearch(request);

        ItemSearchResponse response = amzClient.invoke(new AmzAdvertisingInvoker<ItemSearchResponse>() {
            public ItemSearchResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.itemSearch(itemSearch);
            }
        });

        assertNotNull(response);
        assertTrue(response.getItems().size() > 0);
    }

    @Test
    public void testItemSearchPaging() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setItemPage(new BigInteger("2"));
        request.setSearchIndex("Books");
        request.setKeywords("Java");

        final ItemSearch itemSearch = preparer.prepareItemSearch(request);

        ItemSearchResponse response = amzClient.invoke(new AmzAdvertisingInvoker<ItemSearchResponse>() {
            public ItemSearchResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.itemSearch(itemSearch);
            }
        });

        assertNotNull(response);
        assertTrue(response.getItems().size() > 0);
    }

    /**
     * browser node of a product is its category
     */
    @Test
    public void testItemLookup(){
        ItemLookupRequest request = new ItemLookupRequest();
        request.getItemId().add("B01N2XKCEX");
        request.getResponseGroup().add("Medium");
        request.getResponseGroup().add("BrowseNodes");
        final ItemLookup itemLookup = preparer.prepareItemLookup(request);

        ItemLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<ItemLookupResponse>() {
            public ItemLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.itemLookup(itemLookup);
            }
        });

        Items items= response.getItems().get(0);
        assertNotNull(items);
        Item item = items.getItem().get(0);
        assertNotNull(item);
    }

    /**
     * get browser information of product "B01N2XKCEX"
     */
    @Test
    public void testBrowserNodeLookOfUSBLamp(){
        BrowseNodeLookupRequest request = new BrowseNodeLookupRequest();
        request.getBrowseNodeId().add("3015418011");

        final BrowseNodeLookup browseNodeLookup = preparer.prepareBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<BrowseNodeLookupResponse>() {
            public BrowseNodeLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.browseNodeLookup(browseNodeLookup);
            }
        });

        assertNotNull(response);
    }

    @Test
    public void testBrowserNodeLook2(){
        BrowseNodeLookupRequest request = new BrowseNodeLookupRequest();
        request.getBrowseNodeId().add("11548961011");

        final BrowseNodeLookup browseNodeLookup = preparer.prepareBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<BrowseNodeLookupResponse>() {
            public BrowseNodeLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.browseNodeLookup(browseNodeLookup);
            }
        });

        assertNotNull(response);
    }

    @Test
    public void testSimilarityLookup() {
        SimilarityLookupRequest request = new SimilarityLookupRequest();
        request.getItemId().add("B01N9GQSVA");

        final SimilarityLookup similarityLookup = preparer.prepareSimilarityLookup(request);


        SimilarityLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<SimilarityLookupResponse>() {
            public SimilarityLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.similarityLookup(similarityLookup);
            }
        });

        assertNotNull(response);
        Object item = response.getItems().get(0).getItem().get(0);
        assertNotNull(item);

    }


}
