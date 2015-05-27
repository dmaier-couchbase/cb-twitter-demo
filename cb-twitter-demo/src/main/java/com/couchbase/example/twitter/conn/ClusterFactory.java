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

package com.couchbase.example.twitter.conn;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.example.twitter.cfg.CouchbaseConfig;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class ClusterFactory {
    
    private static Cluster cluster;
    
    
    public static Cluster getCluster() throws IOException
    {
        if (cluster == null)
            createCluster();
       
        return cluster;
    }
    
    public static Cluster createCluster() throws IOException
    {
        
        //Create the cluster reference
        String[] hosts = new CouchbaseConfig().getHosts();
        
        List<String> nodes = Arrays.asList(hosts);
                          
        CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().kvEndpoints(2).viewEndpoints(2).build();
      
        cluster = CouchbaseCluster.create(env, nodes);
        
        
        return cluster;
    }
}
