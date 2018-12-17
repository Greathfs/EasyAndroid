package com.hfs.easyhttp;

import android.app.Application;
import android.content.Context;

import com.hfs.easyhttp.model.HttpHeaders;
import com.hfs.easyhttp.model.HttpParams;
import com.hfs.easyhttp.request.GetRequest;
import com.hfs.easyhttp.request.PostRequest;
import com.hfs.easyhttp.utils.PostType;

import org.xutils.x;

/**
 * @author HuangFusheng
 * @date 2018/12/15
 * @description EasyHttp
 */
public final class EasyHttp {

    private static Context sContext;
    private static volatile EasyHttp sInstance;

    private HttpHeaders mCommonHeaders;
    private HttpParams mCommonParams;

    private EasyHttp() {
    }

    public static EasyHttp getInstance() {
        if (sInstance == null) {
            synchronized (EasyHttp.class) {
                if (sInstance == null) {
                    sInstance = new EasyHttp();
                }
            }
        }
        return sInstance;
    }

    /**
     * 提供全局替换图片加载框架的接口，若切换其它框架，可以实现一键全局替换
     */
    public void init(Application context) {
        sContext=context;
        x.Ext.init(context);
        x.Ext.setDebug(true);
    }

    /**
     * 添加全局公共请求参数
     */
    public EasyHttp addCommonParams(HttpParams commonParams) {
        if (mCommonParams == null) {
            mCommonParams = new HttpParams();
        }
        mCommonParams.put(commonParams);
        return this;
    }

    /**
     * 获取全局公共请求参数
     */
    public HttpParams getCommonParams() {
        return mCommonParams;
    }

    /**
     * 获取全局公共请求头
     */
    public HttpHeaders getCommonHeaders() {
        return mCommonHeaders;
    }

    /**
     * 添加全局公共请求参数
     */
    public EasyHttp addCommonHeaders(HttpHeaders commonHeaders) {
        if (mCommonHeaders == null) {
            mCommonHeaders = new HttpHeaders();
        }
        mCommonHeaders.put(commonHeaders);
        return this;
    }



    public GetRequest get(String url, int requestCode) {
        return new GetRequest(url,requestCode);
    }

    public PostRequest post(String url, int requestCode) {
        return new PostRequest(url,requestCode,PostType.NULL);
    }

    public PostRequest postWithForm(String url, int requestCode) {
        return new PostRequest(url,requestCode,PostType.WITH_FORM);
    }

    public PostRequest postWithJson(String url, int requestCode) {
        return new PostRequest(url,requestCode,PostType.WITH_JSON);
    }


}
