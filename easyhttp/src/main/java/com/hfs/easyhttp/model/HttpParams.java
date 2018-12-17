
package com.hfs.easyhttp.model;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author HuangFusheng
 * @date 2018/12/16
 * @description 普通参数
 */
public class HttpParams implements Serializable {
    /**
     * 普通的键值对参数
     */
    public LinkedHashMap<String, String> urlParamsMap;

    public HttpParams() {
        init();
    }

    public HttpParams(String key, String value) {
        init();
        put(key, value);
    }

    private void init() {
        urlParamsMap = new LinkedHashMap<>();
    }

    public void put(HttpParams params) {
        if (params != null) {
            if (params.urlParamsMap != null && !params.urlParamsMap.isEmpty()) {
                urlParamsMap.putAll(params.urlParamsMap);
            }

        }
    }

    public void put(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return;
        }
        urlParamsMap.putAll(params);
    }

    public void put(String key, String value) {
        urlParamsMap.put(key, value);
    }



    public void removeUrl(String key) {
        urlParamsMap.remove(key);
    }


    public void remove(String key) {
        removeUrl(key);
    }

    public void clear() {
        urlParamsMap.clear();
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParamsMap.entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }

        return result.toString();
    }
}