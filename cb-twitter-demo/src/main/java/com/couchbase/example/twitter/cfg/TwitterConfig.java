package com.couchbase.example.twitter.cfg;

import java.io.IOException;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class TwitterConfig extends BaseConfig {

    public static final String TOKEN_ACCESS = "token.access";
    public static final String TOKEN_SECRET = "token.secret";
    public static final String CONSUMER_KEY = "consumer.key";
    public static final String CONSUMER_SECRET = "consumer.secret";
    
    public TwitterConfig() throws IOException {
       
        super("twitter.properties");
    }
    
}
