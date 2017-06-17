package com.feint.network;

import com.feint.bean.UrlObj;
import com.feint.bean.UserInfo;
import com.feint.bean.meta.NumberLong;
import com.feint.utilities.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by feint on 17/6/16.
 */
public class UrlPatcher {
    private Document mDoc;

    public void connect(String url) throws IOException {
        mDoc= Jsoup.connect(url).get();
    }

    public List<UrlObj> patch(String selector,long hid){
        List<UrlObj> urlObjList=new ArrayList<>();

        Elements elements=mDoc.select(selector);

        if(null==elements)
            return urlObjList;

        for(Element element:elements){
            UrlObj urlObj=new UrlObj();
            urlObj.setUrl(element.attr(HttpUtils.ATTR_HREF));
            urlObj.setDescription(element.text());
            urlObj.setPatchDate(new Date().toString());

            urlObj.setHid(hid);
            urlObjList.add(urlObj);
        }

        return urlObjList;
    }

    public UserInfo patchDetail(String selector){
        Element element=mDoc.select(selector).first();

        UserInfo userInfo=new UserInfo();

        userInfo.setPhone(element.select(HttpUtils.TAG_IMG).attr(HttpUtils.ATTR_SRC));

        userInfo.setName(element.select(HttpUtils.TAG_EM).text());

        userInfo.setUpdateDate(new Date().toString());

        return userInfo;
    }
}
