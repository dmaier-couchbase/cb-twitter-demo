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
public class CouchbaseConfig extends BaseConfig {
 
    public static final String FILE_NAME = "cb.properties";
    public static final String CON_HOSTS = "cb.con.hosts";
    public static final String BUCKET_NAME = "cb.con.bucket.name";
    public static final String BUCKET_PWD = "cb.con.bucket.pwd";
   
    
    
    public CouchbaseConfig() throws IOException {
        super(FILE_NAME);
    }

    public String[] getHosts() {
        
        String propsStr = this.props.getProperty(CON_HOSTS);
        return propsStr.split(",");
    }
    
    public String getBucket() {
    
        return props.getProperty(BUCKET_NAME);
        
    }
    
    public String getPassword() {
        return this.props.getProperty(BUCKET_PWD); 
    }  
    
}
