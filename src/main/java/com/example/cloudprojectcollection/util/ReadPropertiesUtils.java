package com.example.cloudprojectcollection.util;

import java.util.Properties;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日 11:32:39
 * @version: 0.0.1
 * @Description: 读取配置信息处理类
 */
public class ReadPropertiesUtils {

    /**
     * 获取zfbinfo.properties文件里的值
     * @param name key
     * @return
     * @throws Exception
     */
    public String getZFBinfoValue(String name) throws Exception{
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/zfbinfo.properties"));
        String filepath = props.getProperty(name);;
        return filepath;
    }

}
