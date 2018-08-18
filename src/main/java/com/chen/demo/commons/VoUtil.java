package com.chen.demo.commons;

import com.chen.demo.vos.OneData;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.Map;

public class VoUtil {

    private static final Logger log = Logger.getLogger(VoUtil.class);
    private static final Gson gson = new Gson();

    public static Object voToModel(OneData one, Class<?> clazz) throws Exception{
        log.info("[vo         ]:" + gson.toJson(one));
        if(one == null) return null;
        Map<String, Object> map = BeanUtil.objectToMap(one);
        Object obj = BeanUtil.mapToObject(map, clazz);
        log.info("[vo to model]:" + gson.toJson(obj));
        return obj;
    }
}
