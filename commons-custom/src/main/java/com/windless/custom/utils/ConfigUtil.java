package com.windless.custom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 类名: ConfigUtil
 * 功能描述: 模拟springboot从本地获取配置文件，此工具只能读取properties
 *          配置文件读取顺序如下 classpath > classpath:config > $(user.dir) > $(user.dir)/config
 *          由于新的数据会覆盖旧的数据，若四个文件都存在，最终有效的是$(user.dir)/config下的配置
 *          请注意：在多模块的项目中，本地ide中运行时$(user.dir)指向的是终极父项目的位置。
 * 代码版本: 1.0
 * @author windless
 */
public class ConfigUtil {
    private static final String suffix = ".properties";
    private static final String fileName = "application";
    private static final String userDir = System.getProperty("user.dir");
    private static Properties properties = null;

    private synchronized static Properties getConfig(){
        if(properties==null) {
            properties = new Properties();
            loadClassPathProperties(getClassPath(false));
            loadClassPathProperties(getClassPath(true));
            loadUserDirProperties(getUserDirPath(false));
            loadUserDirProperties(getUserDirPath(true));
        }
        return properties;
    }

    private synchronized static void loadClassPathProperties(String filePath){
        try {
            ClassLoader cL = Thread.currentThread().getContextClassLoader();
            if (cL == null) {
                cL = ConfigUtil.class.getClassLoader();
            }
            InputStream inputStream = cL.getResourceAsStream(filePath);
            if(inputStream != null){
                properties.load(inputStream);
                inputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private synchronized static void loadUserDirProperties(String filePath){
        Path path = Paths.get(filePath);
        if(Files.exists(path)){
            try {
                properties.load(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getClassPath(Boolean isConfig){
        StringBuilder fullPath = new StringBuilder();
        if(isConfig){
            fullPath.append("config").append(File.separator);
        }
        fullPath.append(fileName).append(suffix);
        return fullPath.toString();
    }

    private static String getUserDirPath(Boolean isConfig){
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(userDir);
        if(isConfig){
            fullPath.append(File.separator).append("config");
        }
        fullPath.append(File.separator).append(fileName).append(suffix);
        return fullPath.toString();
    }

    public static String getString(String key,String defaultValue){
        return getConfig().getProperty(key,defaultValue);
    }

    public static boolean getBoolean(String key,Boolean defaultValue){
        return getConfig().containsKey(key)?Boolean.parseBoolean(getConfig().getProperty(key)):defaultValue;
    }

    public static short getShort(String key,short defaultValue){
        return getConfig().containsKey(key)?Short.parseShort(getConfig().getProperty(key)):defaultValue;
    }

    public static int getInt(String key,int defaultValue){
        return getConfig().containsKey(key)?Integer.parseInt(getConfig().getProperty(key)):defaultValue;
    }

    public static long getLong(String key,long defaultValue){
        return getConfig().containsKey(key)?Long.parseLong(getConfig().getProperty(key)):defaultValue;
    }

    public static float getFloat(String key,float defaultValue){
        return getConfig().containsKey(key)?Float.parseFloat(getConfig().getProperty(key)):defaultValue;
    }

    public static double getDouble(String key,double defaultValue){
        return getConfig().containsKey(key)?Double.parseDouble(getConfig().getProperty(key)):defaultValue;
    }
}
