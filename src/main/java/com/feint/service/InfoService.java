package com.feint.service;

import com.feint.mapper.UrlObjMapper;
import com.feint.network.UrlPatcher;
import com.feint.utilities.JsonUtils;
import com.feint.utilities.PluginUtils;
import com.feint.utilities.builder.ResponseBuilder;
import com.feint.utilities.factory.MongoDbFactory;
import com.mongodb.client.MongoDatabase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by feint on 17/6/17.
 */
public class InfoService {
    private UrlObjMapper urlObjMapper;

    public InfoService(){
        MongoDatabase db= MongoDbFactory.create();
        urlObjMapper=new UrlObjMapper(db);
    }

    public void readResult(HttpServletRequest request, HttpServletResponse response){
        int page=Integer.parseInt(request.getParameter("page"));

        try {
            new ResponseBuilder().parse(response)
                    .contentType(ResponseBuilder.CONT_TYPE_JSON)
                    .characterEncoding(ResponseBuilder.CHAR_ENCODING_UTF8)
                    .body(JsonUtils.parse(urlObjMapper.pageSelect(page)))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDetail(HttpServletRequest request, HttpServletResponse response){

        try {
            UrlPatcher urlPatcher=new UrlPatcher();
            urlPatcher.connect(request.getParameter("url"));
            new ResponseBuilder().parse(response)
                    .contentType(ResponseBuilder.CONT_TYPE_JSON)
                    .characterEncoding(ResponseBuilder.CHAR_ENCODING_UTF8)
                    .body(JsonUtils.parse(urlPatcher.patchDetail(PluginUtils.Patcher.GANJI_DETAIL_SELECTOR)))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
