package com.uaes.esw.util.restful;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author zhenghuan.wang
 */
public class JiraConstant {
    private static Map<String, String> JIRA = new HashMap<String, String>();

    static {
        initJira();
    }

    public static void main(String[] args) {
        Map<String, String> jiraConstant = JiraConstant.getJIRA();
        System.out.println(jiraConstant);
    }

    public static Map<String, String> getJIRA() {
        return JIRA;
    }

    /**
     * 加载jira.properties配置信息
     */
    public static void initJira() {
        InputStream is = null;
        Properties props = new Properties();
        try {
            is = JiraConstant.class.getResourceAsStream("/jira.properties");
            props.load(is);
            Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
            Entry<Object, Object> entry = null;
            String key = null;
            String value = null;
            while (it.hasNext()) {
                entry = it.next();
                key = entry.getKey().toString();
                value = entry.getValue().toString();
                getJIRA().put(key, value);
            }
        } catch (IOException e) {
            System.out.print("加载jira.properties资源文件出错。");
            e.printStackTrace();
            return;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
