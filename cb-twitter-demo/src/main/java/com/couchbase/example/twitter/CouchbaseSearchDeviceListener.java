/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.couchbase.example.twitter;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.twitterapime.model.MetadataSet;
import com.twitterapime.search.SearchDeviceListener;
import com.twitterapime.search.Tweet;
import com.twitterapime.search.TweetEntity;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class CouchbaseSearchDeviceListener implements SearchDeviceListener {
    
    private static final Logger LOG = Logger.getLogger(CouchbaseSearchDeviceListener.class.getName());
    
    @Override
    public void tweetFound(Tweet tweet) {
        
        String user = tweet.getUserAccount().getString(MetadataSet.USERACCOUNT_NAME);
        String msg = tweet.getString(MetadataSet.TWEET_CONTENT);
        String location = tweet.getUserAccount().getString(MetadataSet.USERACCOUNT_LOCATION);
        Date date = tweet.getDate(MetadataSet.TWEET_PUBLISH_DATE);
        String id = tweet.getString(MetadataSet.TWEET_ID);


        String url = null;
        TweetEntity[] entities = tweet.getEntity().getURLs();
        
        if (entities.length != 0)
        {
            url = entities[0].getString(MetadataSet.TWEETENTITY_URL);
        }
        
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
        

        //String content = tweet.toString();
        
        JsonObject obj = JsonObject.empty();
        obj.put("user", user);
        obj.put("msg", msg);
        obj.put("location", location);
        obj.put("date", date.getTime());
        obj.put("length", msg.getBytes().length);
        
        if (url != null) obj.put("url", url);
        if (tag != null) obj.put("tags", tag);
        
        
        JsonDocument doc = JsonDocument.create(id, obj);
        
        LOG.info(doc.toString());
        //LOG.info(content);
        
        
        //TODO: Load to Couchbase
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
    
}
