 /*
  * Copyright 2015 Couchbase, Inc.
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package com.couchbase.example.twitter;

import com.couchbase.example.twitter.cfg.TwitterConfig;
import com.twitterapime.rest.Credential;
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
        //Access the configuration
        TwitterConfig cfg = new TwitterConfig();
        String tokenAccess = cfg.getTokenAccess();
        String tokenSecret = cfg.getTokenSecret();
        String consumerKey = cfg.getConsumerKey();
        String consumerSecret = cfg.getConsumerSecret();
        
        //Create a token and credentials
        Token t = new Token(tokenAccess, tokenSecret);
        Credential c = new Credential(consumerKey, consumerSecret, t);
        UserAccountManager m = UserAccountManager.getInstance(c);
        
        if (m.verifyCredential()) return m;
       
        //By default return null
        return null;
    }
        
}
