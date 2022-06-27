package com.example.cloudprojectcollection.util;
/**
 * 文件名：ObjectToMap.java
 * 版权： 北京联众信安成都分公司
 * 描述： 对象转换为MapUtil
 * 创建时间：2017年10月18日
 */

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日 12:19:40
 * @version:
 * @Description: 对象转换为MapUtil〈功能详细描述〉
 */
public class ObjectToMap {
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }
}
