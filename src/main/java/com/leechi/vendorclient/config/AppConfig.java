package com.leechi.vendorclient.config;


public class AppConfig {
    private  String awsAdvertisingUrl;
    private  String awsAssociateAccessKeyId;
    private  String awsAssociateSecret;
    private  String awsAssociateTag;
    private static AppConfig  _singleton = null;

    private AppConfig(String awsAdvertisingUrl, String awsAssociateAccessKeyId, String awsAssociateSecret, String awsAssociateTag) {
        this.awsAdvertisingUrl = awsAdvertisingUrl;
        this.awsAssociateAccessKeyId = awsAssociateAccessKeyId;
        this.awsAssociateSecret = awsAssociateSecret;
        this.awsAssociateTag = awsAssociateTag;
    }

    public static AppConfig singleton(){
        if (_singleton == null) {
            String endpoint = ConfigParser.getValue("aws.advertising.endpoint");
            String accessKeyId = ConfigParser.getValue("aws.associate.accesskeyId");
            String secret = ConfigParser.getValue("aws_associate_secret");
            String tag = ConfigParser.getValue("aws.associate.tag");

            _singleton = new  AppConfig(endpoint, accessKeyId, secret, tag);
        }

       return  _singleton;
    }

    /**
     *
     * @return awsAdvertisingUrl
     */
    public String getAwsAdvertisingUrl() {
        return awsAdvertisingUrl;
    }

    /**
     *
     * @return awsAssociateAccessKeyId
     */
    public String getAwsAssociateAccessKeyId() {
        return awsAssociateAccessKeyId;
    }

    /**
     *
     * @return awsAssociateSecret
     */
    public String getAwsAssociateSecret() {
        return awsAssociateSecret;
    }

    /**
     *
     * @return  awsAssociateTag
     */
    public String getAwsAssociateTag() {
        return awsAssociateTag;
    }


}
