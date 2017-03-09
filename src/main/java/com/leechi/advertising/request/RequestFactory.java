package com.leechi.advertising.request;


import com.amazon.advertising.jaxws.*;
import com.leechi.advertising.config.AppConfig;

public  class RequestFactory {
    private static  AppConfig config = AppConfig.singleton();

    public static  ItemSearch makeItemSearch(ItemSearchRequest itemSearchRequest){
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setAssociateTag(config.getAssociateTag());
        itemSearch.setAWSAccessKeyId(config.getAccessKeyId());
        itemSearch.getRequest().add(itemSearchRequest);
        return itemSearch;
    }

    public static ItemLookup makeItemLookup(ItemLookupRequest itemLookupRequest){
        ItemLookup itemLookup = new ItemLookup();
        itemLookup.setAssociateTag(config.getAssociateTag());
        itemLookup.setAWSAccessKeyId(config.getAccessKeyId());
        itemLookup.getRequest().add(itemLookupRequest);
        return itemLookup;
    }

    public static SimilarityLookup makeSimilarityLookup(SimilarityLookupRequest request){
        SimilarityLookup similarityLookup = new SimilarityLookup();
        similarityLookup.setAssociateTag(config.getAssociateTag());
        similarityLookup.setAWSAccessKeyId(config.getAccessKeyId());
        similarityLookup.getRequest().add(request);
        return similarityLookup;
    }

    public static BrowseNodeLookup makeBrowseNodeLookup(BrowseNodeLookupRequest request) {
        BrowseNodeLookup browseNodeLookup = new BrowseNodeLookup();
        browseNodeLookup.setAssociateTag(config.getAssociateTag());
        browseNodeLookup.setAWSAccessKeyId(config.getAccessKeyId());
        browseNodeLookup.getRequest().add(request);

        return browseNodeLookup;
    }
}
