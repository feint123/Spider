package com.feint.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feint.bean.UrlObj;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by feint on 17/6/16.
 */
public class UrlObjMapper extends Mapper{
    private MongoCollection<Document> collection;
    private static final int pageCount=10;

    public UrlObjMapper(MongoDatabase mongoDatabase) {
        super(mongoDatabase);
        collection=mDb.getCollection("url_obj");
    }

    public void insert(UrlObj urlObj) throws JsonProcessingException {
        collection.insertOne(Document.parse(new ObjectMapper().writeValueAsString(urlObj)));

    }

    public List<UrlObj> pageSelect(int page) throws IOException {
        FindIterable<Document> iterable=collection.find().skip((page-1)*pageCount).limit(pageCount);
        List<UrlObj> urlObjList=new LinkedList<>();

        for(Document document:iterable){
            document.remove("_id");
            UrlObj urlObj=new UrlObj();
            urlObj.setHid(document.getLong("hid"));
            urlObj.setUrl(document.getString("url"));
            urlObj.setPatchDate(document.getString("patchDate"));
            urlObj.setDescription(document.getString("description"));
            urlObjList.add(urlObj);
        }

        return urlObjList;
    }
}
