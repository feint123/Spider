package com.feint.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feint.bean.UrlHistory;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.codecs.Decoder;


import java.io.IOException;

/**
 * Created by feint on 17/6/16.
 */
public class UrlHistoryMapper extends Mapper {

    public UrlHistoryMapper(MongoDatabase mongoDatabase) {
        super(mongoDatabase);
    }

    public UrlHistory selectOne(String url) throws IOException {
        UrlHistory urlHistory = null;
        MongoCollection<Document> collection = mDb.getCollection("url_history");
        BasicDBObject dbObject = new BasicDBObject("url", url);
        if(null==collection.find(dbObject).first())
            return urlHistory;
        urlHistory = new ObjectMapper().readValue(collection.find(dbObject).first().toJson(), UrlHistory.class);
        return urlHistory;
    }

    public void insert(UrlHistory urlHistory) throws JsonProcessingException {
        MongoCollection<Document> collection = mDb.getCollection(("url_history"));
        System.out.println(new ObjectMapper().writeValueAsString(urlHistory));
        collection.insertOne(Document.parse(new ObjectMapper().writeValueAsString(urlHistory)));
    }
}
