package com.feint.utilities.builder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by feint on 17/6/16.
 */
public class ResponseBuilder {
    public static final String CONT_TYPE_JSON="application/json";
    public static final String CONT_TYPE_HTML="text/html";

    public static final String CHAR_ENCODING_UTF8="utf-8";
    public static final String CHAR_ENCODING_ZH="gb2312";

    private HttpServletResponse mResponse;
    public ResponseBuilder parse(HttpServletResponse response){
        mResponse=response;
        mResponse.setHeader("Access-Control-Allow-Origin","*");
        return this;
    }

    public ResponseBuilder contentType(String contentType){
        mResponse.setContentType(contentType);

        return this;
    }

    public ResponseBuilder characterEncoding(String character){
        mResponse.setCharacterEncoding(character);

        return this;
    }

    public ResponseBuilder body(String content) throws IOException {
        mResponse.getWriter().write(content);
        return this;
    }

    public HttpServletResponse build(){
        return mResponse;
    }


}
