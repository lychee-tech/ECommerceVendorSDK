package com.leechi.vendorclient.querypreparer;


import com.amazon.advertising.jaxws.*;
import com.leechi.vendorclient.config.AppConfig;

public  class AmzAdvertisingQueryPreparer {
    private static  AppConfig config = AppConfig.singleton();

    public static  ItemSearch prepareItemSearch(ItemSearchRequest itemSearchRequest){
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setAssociateTag(config.getAwsAssociateTag());
        itemSearch.setAWSAccessKeyId(config.getAwsAssociateAccessKeyId());
        itemSearch.getRequest().add(itemSearchRequest);
        return itemSearch;
    }

    public static ItemLookup prepareItemLookup(ItemLookupRequest itemLookupRequest){
        ItemLookup itemLookup = new ItemLookup();
        itemLookup.setAssociateTag(config.getAwsAssociateTag());
        itemLookup.setAWSAccessKeyId(config.getAwsAssociateAccessKeyId());
        itemLookup.getRequest().add(itemLookupRequest);
        return itemLookup;
    }

    public static SimilarityLookup prepareSimilarityLookup(SimilarityLookupRequest request){
        SimilarityLookup similarityLookup = new SimilarityLookup();
        similarityLookup.setAssociateTag(config.getAwsAssociateTag());
        similarityLookup.setAWSAccessKeyId(config.getAwsAssociateAccessKeyId());
        similarityLookup.getRequest().add(request);
        return similarityLookup;
    }

    public static BrowseNodeLookup prepareBrowseNodeLookup(BrowseNodeLookupRequest request) {
        BrowseNodeLookup browseNodeLookup = new BrowseNodeLookup();
        browseNodeLookup.setAssociateTag(config.getAwsAssociateTag());
        browseNodeLookup.setAWSAccessKeyId(config.getAwsAssociateAccessKeyId());
        browseNodeLookup.getRequest().add(request);

        return browseNodeLookup;
    }
}
