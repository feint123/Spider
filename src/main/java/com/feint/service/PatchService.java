package com.feint.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feint.bean.ResponseEntity;
import com.feint.bean.UrlHistory;
import com.feint.bean.UrlObj;
import com.feint.bean.UserInfo;
import com.feint.mapper.UrlHistoryMapper;
import com.feint.mapper.UrlObjMapper;
import com.feint.network.UrlPatcher;
import com.feint.utilities.DbUtils;
import com.feint.utilities.PluginUtils;
import com.feint.utilities.builder.ResponseBuilder;
import com.feint.utilities.factory.MongoDbFactory;
import com.mongodb.client.MongoDatabase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by feint on 17/6/16.
 */
public class PatchService {
    private UrlObjMapper urlObjMapper;
    private HistoryService historyService;
    public PatchService(){
        MongoDatabase db= MongoDbFactory.create();
        urlObjMapper=new UrlObjMapper(db);

        historyService=new HistoryService();
    }

    public void patch(HttpServletRequest request,HttpServletResponse response){

        ResponseEntity responseEntity=new ResponseEntity();


        try {
            String url=request.getParameter("url");
            if(historyService.hasUrl(url)){
                responseEntity.setCode(500);
                new ResponseBuilder().parse(response)
                        .characterEncoding(ResponseBuilder.CHAR_ENCODING_UTF8)
                        .contentType(ResponseBuilder.CONT_TYPE_JSON)
                        .body(new ObjectMapper().writeValueAsString(responseEntity))
                        .build();
                return;
            }

            long hid=historyService.addHistory(url);
            UrlPatcher patcher=new UrlPatcher();
            patcher.connect(url);
            List<UrlObj> urlObjList=patcher.patch(PluginUtils.Patcher.GANJI_SELECTOR,hid);
            for(UrlObj urlObj:urlObjList)
                urlObjMapper.insert(urlObj);

            responseEntity.setCode(200);

            new ResponseBuilder().parse(response)
                    .contentType(ResponseBuilder.CONT_TYPE_JSON)
                    .characterEncoding(ResponseBuilder.CHAR_ENCODING_UTF8)
                    .body(new ObjectMapper().writeValueAsString(responseEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
