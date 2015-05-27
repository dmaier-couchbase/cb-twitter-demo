# Couchbase Twitter Demo

This is just a simple example application which shows how to load twitter feeds from your account into Couchbase. You need to have a Twitter Application in order to get started (http://apps.twitter.com). The application authentication details can then be used in order to access your twitter account.

A file twitter.properties needs to be created in the src/main/java directory (root package and so root of the class path). It should contain the following properties:

```
token.access=xyz
token.secret=abc
consumer.key=123
consumer.secret=098
```

You should also edit the 'cb.properties' file in order to add your Couchbase connection.

```
cb.con.hosts=192.168.7.160
cb.con.bucket.name=twitter
cb.con.bucket.pwd=test
```

The demo is quite simple. It does the following:

* Uses the 'Twitter API ME' in order to read all tweets from you and on your home screen
* Stores them into Couchbase if they are not yet there

The following properties are stored:

* User
* Message
* Location (optional)
* Publish date
* Id (as part of the key)
* Tags (optional)
* First mentioned URL (optional)
* Length of the message

The idea of the demo is just to fill a Couchbase bucket with some meaningful demo data. Because old tweets are skipped you can easily create new tweets on side of Twitter and then rerun the tool. This will give you new items in your Couchbase cluster. An example demonstration regarding real-time analysis with Couchbase and Elasticsearch could be:

* Run the tool
* Show the tweets in a Couchbase Bucket - Couchbase is used as a database which supports hundreds of thousands of operations per second whereby Elasticsearch is used for full-text indexing.
* Index the feeds by using XDCR/Couchbase's Elasticsearch plug-in
* Show a Kibana report regarding the occourence of Couchbase in the message text
* Create a new Twitter feed
* Run the tool again
* Show how the value changed in your report


