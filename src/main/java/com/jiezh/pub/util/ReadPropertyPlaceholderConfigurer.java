package com.jiezh.pub.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ReadPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private static Map<String, Object> propertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertiesMap = new HashMap<>();
        Iterator var3 = props.keySet().iterator();

        while(var3.hasNext()) {
            Object key = var3.next();
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertiesMap.put(keyStr, value);
        }

    }

    public static Object getContextProperty(String name) {
        Object obj = propertiesMap.get(name);
        return obj == null ? name : obj;
    }
}
