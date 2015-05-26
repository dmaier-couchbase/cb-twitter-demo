package com.couchbase.example.twitter;

import com.couchbase.example.twitter.cfg.TwitterConfig;
import com.twitterapime.rest.Credential;
import com.twitterapime.rest.TweetER;
import com.twitterapime.rest.UserAccountManager;
import com.twitterapime.search.LimitExceededException;
import com.twitterapime.xauth.Token;
import java.io.IOException;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class TwitterClient {
    
    public static UserAccountManager connect() throws IOException, LimitExceededException
    {
        TwitterConfig cfg = new TwitterConfig();

        String tokenAccess = cfg.getProps().getProperty(TwitterConfig.TOKEN_ACCESS);
        String tokenSecret = cfg.getProps().getProperty(TwitterConfig.TOKEN_SECRET);
        String consumerKey = cfg.getProps().getProperty(TwitterConfig.CONSUMER_KEY);
        String consumerSecret = cfg.getProps().getProperty(TwitterConfig.CONSUMER_SECRET);
        
        Token t = new Token(tokenAccess, tokenSecret);
        
        Credential c = new Credential(consumerKey, consumerSecret, t);
        
        UserAccountManager m = UserAccountManager.getInstance(c);
        
        if (m.verifyCredential()) return m;
       
        //By default return null
        return null;
    }
        
}
