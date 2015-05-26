package com.couchbase.example.twitter;

import com.twitterapime.rest.Timeline;
import com.twitterapime.rest.UserAccountManager;


/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        UserAccountManager auth = TwitterClient.connect();
        
        if (auth != null)
        {
            System.out.println("Connection successful!");
            
            Timeline tl = Timeline.getInstance(auth);
            
            tl.startGetHomeTweets(null, new CouchbaseSearchDeviceListener());
            tl.startGetUserTweets(null, new CouchbaseSearchDeviceListener());
        }
    }
}
