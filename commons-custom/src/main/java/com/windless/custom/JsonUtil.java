package com.windless.custom;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: JsonUtil
 * 功能描述: Json工具类
 * 代码版本: Since 1.0
 * @author zhanggangxue
 */
public class JsonUtil {

    public static JSONObject toJson(Object value){
        if(value != null){
            String jsonStr = JSONObject.toJSONString(value);
            return JSONObject.parseObject(jsonStr);
        }
        return null;
    }

    public static JSONObject getSudJsonAuto(JSONObject parent,String key){
        JSONObject json = getSudJson(parent,key);
        if(json == null){
            json = new JSONObject();
        }
        return json;
    }

    public static JSONObject getSudJson(JSONObject parent,String key){
        if(parent != null && key != null){
            if(parent.containsKey(key)){
                return parent.getJSONObject(key);
            }
        }
        return null;
    }
}
