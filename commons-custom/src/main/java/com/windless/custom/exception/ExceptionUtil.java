package com.windless.custom.exception;

import com.windless.custom.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名: ExceptionUtil
 * 功能描述: 异常处理工具类
 * 代码版本: Since 1.0
 * @author zhanggangxue
 */
public class ExceptionUtil {
    private static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    /************************************* 字符串区 **************************************/
    /**
     * 方法名: checkStrEmpty
     * 功能描述: 检验字符型参数是否为空，是空则抛出异常
     * @param value 字符型参数
     * @param message 返回前端的提示信息，为null则返回默认提示
     * @return void 无
     */
    public static void checkStrEmpty(String value,String message){
        if(StringUtil.isEmpty(value)){
            makeException(CodeEnum.CODE_A0410,message);
        }
    }


    public static void checkStrPattern(String reg,String value,String message){
        if(StringUtil.notEmpty(value)){
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(value);
            if(!matcher.matches()){
                makeException(CodeEnum.CODE_A0402,message);
            }

        }
    }
    /*************************************** 数字区 ****************************************/
    /**
     * 方法名: checkNumEmpty
     * 功能描述: 检验数字型参数是否为空，是空则抛出异常
     * @param value 数字型参数
     * @param message 返回前端的提示信息，为null则返回默认提示
     * @return void 无
     */
    public static void checkNumEmpty(Number value,String message){
        if(value == null){
            makeException(CodeEnum.CODE_A0410,message);
        }
    }


    /*************************************** 公共区域 ****************************************/
    /**
     * 方法名: makeException
     * 功能描述: 异常产生方法，主要处理提示信息
     * @param codeEnum 异常码枚举
     * @param message 自定义提示信息
     * @return void 无
     */
    public static void makeException(CodeEnum codeEnum,String message){
        if(StringUtil.isEmpty(message)){
            throw new BaseException(codeEnum);
        }else{
            throw new BaseException(codeEnum.getCode(),message);
        }
    }
}
