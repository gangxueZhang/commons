package com.windless.custom.cache;

import com.alibaba.fastjson.JSONObject;
import com.windless.custom.JsonUtil;
import com.windless.custom.exception.ExceptionUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名: CacheData
 * 功能描述: 数据缓存
 * 代码版本: Since 1.0
 * @author zhanggangxue
 */
public abstract class CacheData {
    private static final JSONObject CACHE = new JSONObject();
    private static final Map<String,Boolean> KEY_CACHE = new ConcurrentHashMap<>();
    private static final String KEY_REG = "[^\\.]+(\\.[^\\.]+)*";
    /**
     * 方法名: put
     * 功能描述: 存储数据
     * @param key 存储数据key，格式：key.key.key
     * @param value 待存储的数据
     * @return void 无
     */
    public static void put(String key,Object value){
        keyCheck(key);
        String[] keyArray = key.split("\\.");
        saveData(CACHE,keyArray,0,value);
        KEY_CACHE.put(key,true);
    }

    /**
     * 方法名: get
     * 功能描述: 存储数据
     * @param key 存储数据key，格式：key.key.key
     * @return void 无
     */
    public static Object get(String key){
        keyCheck(key);
        String[] keyArray = key.split("\\.");
        if (KEY_CACHE.containsKey(key)){
            return getData(CACHE,keyArray,0);
        }else{
            return null;
        }
    }



    /**
     * 方法名: saveData
     * 功能描述: 递归存储数据
     * @param cacheJson 需要存储数据的父级json
     * @param keyArray 获取数据的key拆分出来的key数组
     * @param index 当前计算到key的下标
     * @param value 待存储的数据
     * @return void 无
     */
    private static void saveData(JSONObject cacheJson,String[] keyArray,int index,Object value){
        if(keyArray != null && index <= keyArray.length - 1){
            String key = keyArray[index];
            if(index >= keyArray.length - 1 ){
                cacheJson.put(key,value);
            }else {
                index = index + 1;
                JSONObject json = JsonUtil.getSudJsonAuto(cacheJson,key);
                saveData(json,keyArray,index,value);
                cacheJson.put(key,json);
            }
        }
    }
    /**
     * 方法名: getData
     * 功能描述: 递归存储数据
     * @param cacheJson 待获取数据的父级json
     * @param keyArray 获取数据的key拆分出来的key数组
     * @param index 当前计算到key的下标
     * @return Object 待取出的数据
     */
    private static Object getData(JSONObject cacheJson,String[] keyArray,int index){
        if(keyArray != null && index <= keyArray.length - 1){
            String key = keyArray[index];
            if(index >= keyArray.length - 1 ){
                return cacheJson.get(key);
            }else {
                index = index + 1;
                JSONObject json = JsonUtil.getSudJsonAuto(cacheJson,key);
                return getData(json,keyArray,index);
            }
        }
        return null;
    }
    private static void keyCheck(String key){
        ExceptionUtil.checkStrEmpty(KEY_REG,"数据key值不能为空值");
        ExceptionUtil.checkStrPattern(KEY_REG,key,"数据key值不合法");
    }
}
