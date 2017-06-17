package com.feint.mapper;

import com.mongodb.client.MongoDatabase;

/**
 * Created by feint on 17/6/16.
 */
public class Mapper {
    protected MongoDatabase mDb;
    public Mapper(MongoDatabase mongoDatabase) {
        mDb = mongoDatabase;
    }
}
