package com.leechi.advertising.config;


public class AppConfig {
    private  String endpoint;
    private  String accessKeyId;
    private  String secretAccessKey;
    private  String associateTag;
    private static AppConfig  _singleton = null;

    private AppConfig(String endpoint, String accessKeyId, String secretAccessKey, String associateTag) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.associateTag = associateTag;
    }

    public static AppConfig singleton(){
        if (_singleton == null) {
            String endpoint = AwsConfig.getValue("aws.endpoint");
            String accessKeyId = AwsConfig.getValue("aws.accesskey.id");
            String secretAccessKey = AwsConfig.getValue("aws_secret_accesskey");
            String associateTag = AwsConfig.getValue("aws.associate.tag");

            _singleton = new  AppConfig(endpoint, accessKeyId, secretAccessKey, associateTag);
        }

       return  _singleton;
    }

    /**
     *
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     *
     * @return accessKeyId
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     *
     * @return secretAccessKey
     */
    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    /**
     *
     * @return  associateTag
     */
    public String getAssociateTag() {
        return associateTag;
    }


}
