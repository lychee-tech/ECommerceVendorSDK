package com.leechi.advertising.client;


import com.amazon.advertising.jaxws.*;
import com.leechi.advertising.request.RequestFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.xml.ws.WebServiceException;



public class AmzClientTest {
    private AmzClient amzClient = new AmzClient();
    @Test
    public void testItemSearch() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setSearchIndex("Books");
        request.setKeywords("Java");

        final ItemSearch itemSearch = RequestFactory.makeItemSearch(request);

        ItemSearchResponse response = amzClient.invoke(new WebServiceInvoker<ItemSearchResponse>() {
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
        final ItemLookup itemLookup = RequestFactory.makeItemLookup(request);

        ItemLookupResponse response= amzClient.invoke(new WebServiceInvoker<ItemLookupResponse>() {
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

        final BrowseNodeLookup browseNodeLookup = RequestFactory.makeBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new WebServiceInvoker<BrowseNodeLookupResponse>() {
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

        final BrowseNodeLookup browseNodeLookup = RequestFactory.makeBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new WebServiceInvoker<BrowseNodeLookupResponse>() {
            public BrowseNodeLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.browseNodeLookup(browseNodeLookup);
            }
        });

        assertNotNull(response);
    }


}
