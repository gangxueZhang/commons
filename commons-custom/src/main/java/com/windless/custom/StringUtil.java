package com.windless.custom;

/**
 * 类名: StringUtil
 * 功能描述: 字符串工具类
 * 代码版本: Since 1.0
 * @author zhanggangxue
 */
public class StringUtil {
    public static final String EMPTY_STR = "";

    public static boolean isEmpty(String value){
        if(value == null || EMPTY_STR.equals(value)){
            return true;
        }
        return false;
    }

    public static boolean notEmpty(String value){
        return !isEmpty(value);
    }


}
