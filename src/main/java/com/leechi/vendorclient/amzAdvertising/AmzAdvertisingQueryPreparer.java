package com.leechi.vendorclient.amzAdvertising;


import com.amazon.advertising.jaxws.*;

public  class AmzAdvertisingQueryPreparer {
    private String awsAssociateAccessKey;
    private String awsAssociateTag;

    private AmzAdvertisingQueryPreparer(String awsAssociateAccessKey, String  awsAssociateTag) {
        this.awsAssociateAccessKey = awsAssociateAccessKey;
        this.awsAssociateTag = awsAssociateTag;
    }

    public   ItemSearch prepareItemSearch(ItemSearchRequest itemSearchRequest){
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setAWSAccessKeyId(awsAssociateAccessKey);
        itemSearch.setAssociateTag(awsAssociateTag);
        itemSearch.getRequest().add(itemSearchRequest);
        return itemSearch;
    }

    public  ItemLookup prepareItemLookup(ItemLookupRequest itemLookupRequest){
        ItemLookup itemLookup = new ItemLookup();
        itemLookup.setAWSAccessKeyId(awsAssociateAccessKey);
        itemLookup.setAssociateTag(awsAssociateTag);
        itemLookup.getRequest().add(itemLookupRequest);
        return itemLookup;
    }

    public  SimilarityLookup prepareSimilarityLookup(SimilarityLookupRequest request){
        SimilarityLookup similarityLookup = new SimilarityLookup();
        similarityLookup.setAWSAccessKeyId(awsAssociateAccessKey);
        similarityLookup.setAssociateTag(awsAssociateTag);
        similarityLookup.getRequest().add(request);
        return similarityLookup;
    }

    public  BrowseNodeLookup prepareBrowseNodeLookup(BrowseNodeLookupRequest request) {
        BrowseNodeLookup browseNodeLookup = new BrowseNodeLookup();
        browseNodeLookup.setAWSAccessKeyId(awsAssociateAccessKey);
        browseNodeLookup.setAssociateTag(awsAssociateTag);
        browseNodeLookup.getRequest().add(request);

        return browseNodeLookup;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class  Builder {
        private String awsAssociateTag;
        private String awsAssociateAccessKey;


        public Builder setAwsAssociateTag(String awsAssociateTag) {
            this.awsAssociateTag = awsAssociateTag;
            return this;
        }


        public Builder setAwsAssociateAccessKey(String awsAssociateAccessKey) {
            this.awsAssociateAccessKey = awsAssociateAccessKey;
            return this;
        }

        public AmzAdvertisingQueryPreparer build(){
            return new AmzAdvertisingQueryPreparer(awsAssociateAccessKey, awsAssociateTag);
        }
    }
}
