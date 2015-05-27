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

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.example.twitter.conn.BucketFactory;
import com.twitterapime.model.MetadataSet;
import com.twitterapime.search.SearchDeviceListener;
import com.twitterapime.search.Tweet;
import com.twitterapime.search.TweetEntity;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class CouchbaseSearchDeviceListener implements SearchDeviceListener {
    
    private static final Logger LOG = Logger.getLogger(CouchbaseSearchDeviceListener.class.getName());
    
    @Override
    public void tweetFound(Tweet tweet) {
        
        //Prepare a JsonDocument based on tweet properties
        String user = tweet.getUserAccount().getString(MetadataSet.USERACCOUNT_NAME);
        String msg = tweet.getString(MetadataSet.TWEET_CONTENT);
        String location = tweet.getUserAccount().getString(MetadataSet.USERACCOUNT_LOCATION);
        Date date = tweet.getDate(MetadataSet.TWEET_PUBLISH_DATE);
        String id = tweet.getString(MetadataSet.TWEET_ID);
        String tag = extractTags(tweet);
        String url = extractUrl(tweet);
   
        JsonObject obj = JsonObject.empty();
        obj.put("user", user);
        obj.put("msg", msg);
        obj.put("date", date.getTime());
        obj.put("length", msg.getBytes().length);
        
        if (!location.equals("")) obj.put("location", location);
        if (url != null) obj.put("url", url);
        if (tag != null) obj.put("tags", tag);
        
        String key = "tweet::" + id;
        
        JsonDocument doc = JsonDocument.create(key, obj);
        
        LOG.info(doc.toString());
                
        //Insert into Couchbase
        try {
            
            Bucket b = BucketFactory.getBucket();
            b.insert(doc);
            
        }
        catch (Exception e)
        {
            LOG.log(Level.INFO, "Could not insert document {0}", key);
        }

    }

    @Override
    public void searchCompleted() {
  
        LOG.info("The search completed");
        
    }

    @Override
    public void searchFailed(Throwable thrwbl) {
        
        LOG.severe("The search failed");
        thrwbl.printStackTrace();
    }
 
    
    /**
     * Extracts all tags from a tweet
     * @param tweet
     * @return 
     */
    private String extractTags(Tweet tweet)
    {
        String tag = null;
        TweetEntity[] tagEntities = tweet.getEntity().getHashtags();
        
        if (tagEntities.length != 0)
        {
            StringBuilder sb = new StringBuilder();
            
            int i = 0;
            
            for (TweetEntity tweetEntity : tagEntities) {
             
                if (i!=0) sb.append(" ");
                sb.append(tweetEntity.getString(MetadataSet.TWEETENTITY_HASHTAG));
                
                i++;
            }
            
            tag = sb.toString();
        }
        
        return tag;
    }
    
    /**
     * Extracts the first URL from a tweet
     * 
     * @param tweet
     * @return 
     */
    private String extractUrl(Tweet tweet)
    {
        String url = null;
        
        TweetEntity[] entities = tweet.getEntity().getURLs();
        
        if (entities.length != 0)
        {
            url = entities[0].getString(MetadataSet.TWEETENTITY_URL);
        }
        
        return url;
    }
    
}
