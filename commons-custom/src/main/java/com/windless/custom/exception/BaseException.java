package com.windless.custom.exception;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名: BaseException
 * 功能描述: 基本的自定义异常，不含业务属性。【通用】
 * 代码版本: Since 1.0
 * @author zhanggangxue
 */
@Getter
public class BaseException extends RuntimeException{
    private static Logger logger = LoggerFactory.getLogger(BaseException.class);
    private String code;

    public BaseException(String code,String message){
        super(message);
        this.code = code;
    }

    public BaseException(CodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    public static BaseException getBaseException(CodeEnum codeEnum,Exception e){
        return getBaseException(codeEnum.getCode(),codeEnum.getMessage(),e);
    }

    public static BaseException getBaseException(String code,String message,Exception e){
        if(e instanceof BaseException){
            return (BaseException)e;
        }else {
            if(logger.isWarnEnabled()){
                logger.warn(e.getMessage() + e);
            }
            return new BaseException(code,message);
        }
    }

}
