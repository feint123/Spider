package com.feint.utilities;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by feint on 17/6/16.
 */
public class DbUtils {

    public static MongoDatabase getDatabase(String host,int port,String dbName){

        MongoClient client=new MongoClient(host,port);

        return client.getDatabase(dbName);

    }
}
