package com.feint.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by feint on 17/6/17.
 */
public class JsonUtils {
    private static ObjectMapper objectMapper=new ObjectMapper();

    public static String parse(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
