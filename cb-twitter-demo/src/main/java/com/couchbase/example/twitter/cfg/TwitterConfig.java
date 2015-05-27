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

package com.couchbase.example.twitter.cfg;

import java.io.IOException;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class TwitterConfig extends BaseConfig {

    public static final String FILE_NAME = "twitter.properties";
    public static final String TOKEN_ACCESS = "token.access";
    public static final String TOKEN_SECRET = "token.secret";
    public static final String CONSUMER_KEY = "consumer.key";
    public static final String CONSUMER_SECRET = "consumer.secret";
    
    public TwitterConfig() throws IOException {
       
        super(FILE_NAME);
    }
    
    public String getTokenAccess()
    {
       return this.props.getProperty(TOKEN_ACCESS);
    }
    
    public String getTokenSecret()
    {
        return this.props.getProperty(TOKEN_SECRET);
    }
    
    public String getConsumerKey()
    {
        return this.props.getProperty(CONSUMER_KEY);
    }
    
    public String getConsumerSecret()
    {
        return this.props.getProperty(CONSUMER_SECRET);
    }
}
