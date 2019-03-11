package com.sofyun.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonHelper {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }
}
