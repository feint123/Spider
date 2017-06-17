package com.feint.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.feint.bean.UrlHistory;
import com.feint.bean.meta.NumberLong;
import com.feint.mapper.UrlHistoryMapper;
import com.feint.utilities.DbUtils;
import com.feint.utilities.PluginUtils;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by feint on 17/6/16.
 */
public class HistoryService {
    private UrlHistoryMapper urlHistoryMapper;

    public HistoryService(){
        MongoDatabase db= DbUtils.getDatabase(
                PluginUtils.Database.URL,
                PluginUtils.Database.PORT,
                PluginUtils.Database.DATABASE_NAME);

        urlHistoryMapper=new UrlHistoryMapper(db);
    }

    public long addHistory(String url){
        long hid=new Timestamp(new Date().getTime()).getTime();
        UrlHistory urlHistory=new UrlHistory();
        urlHistory.setUrl(url);
        urlHistory.setHid(hid);
        try {
            urlHistoryMapper.insert(urlHistory);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return hid;
    }

    public boolean hasUrl(String url){
        try {
            UrlHistory history=urlHistoryMapper.selectOne(url);
            if(null==history);
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
