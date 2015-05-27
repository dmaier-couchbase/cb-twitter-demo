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

import com.twitterapime.rest.Timeline;
import com.twitterapime.rest.UserAccountManager;
import java.util.logging.Logger;


/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class Main {
    
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws Exception {
                
        UserAccountManager auth = TwitterClient.connect();
        
        if (auth != null)
        {   
            LOG.info("Twitter authentication successful, retrieving timelines");
            
            Timeline tl = Timeline.getInstance(auth);
            tl.startGetHomeTweets(null, new CouchbaseSearchDeviceListener());
            tl.startGetUserTweets(null, new CouchbaseSearchDeviceListener());
        }
    }
}
