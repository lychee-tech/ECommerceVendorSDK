package com.leechi.vendorclient.client;


import com.amazon.advertising.jaxws.*;
import com.leechi.vendorclient.Invoker.AmzAdvertisingInvoker;
import com.leechi.vendorclient.querypreparer.AmzAdvertisingQueryPreparer;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.xml.ws.WebServiceException;



public class AmzAdvertisingClientTest {
    private AmzAdvertisingClient amzClient = ClientFactory.getAwsAdvertisingClient();
    @Test
    public void testItemSearch() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setSearchIndex("Books");
        request.setKeywords("Java");

        final ItemSearch itemSearch = AmzAdvertisingQueryPreparer.prepareItemSearch(request);

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
        final ItemLookup itemLookup = AmzAdvertisingQueryPreparer.prepareItemLookup(request);

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

        final BrowseNodeLookup browseNodeLookup = AmzAdvertisingQueryPreparer.prepareBrowseNodeLookup(request);
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

        final BrowseNodeLookup browseNodeLookup = AmzAdvertisingQueryPreparer.prepareBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<BrowseNodeLookupResponse>() {
            public BrowseNodeLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.browseNodeLookup(browseNodeLookup);
            }
        });

        assertNotNull(response);
    }


}
