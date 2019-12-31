package com.lsg.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lsg.exception.BusinessException;



/**
 * @description bean 相关操作
 * @author menggang
 * @date 2017年1月20日
 */
public class BeanUtils {
    private final static String CLASS = "class";


    /**
     * @deccription 将bean转换成map
     * @param obj
     * @return
     * @return Map<String,Object>
     */
    public static Map<String, Object> beanConvertToMap(Object obj) {
        BeanInfo BeanInfo = null;
        PropertyDescriptor[] propertys = null;
        String name = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == obj) {
            return map;
        }
        try {
            BeanInfo = Introspector.getBeanInfo(obj.getClass());
            propertys = BeanInfo.getPropertyDescriptors();
            if (null != propertys && propertys.length > 0) {
                for (PropertyDescriptor property : propertys) {
                    name = property.getDisplayName();
                    if (StringUtils.isNotEmpty(name) && !CLASS.equals(name)) {
                        map.put(name, property.getReadMethod().invoke(obj));
                    }
                }
            }

        } catch (Exception e) {
            new BusinessException(e.getMessage());
        }

        return map;
    }



}
