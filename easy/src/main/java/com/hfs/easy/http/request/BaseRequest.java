package com.hfs.easy.http.request;

import com.hfs.easy.http.EasyHttp;
import com.hfs.easy.http.listener.ICallBack;
import com.hfs.easy.http.model.HttpHeaders;
import com.hfs.easy.http.model.HttpParams;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.http.request.UriRequest;

import java.net.HttpCookie;
import java.util.List;

/**
 * @author HuangFusheng
 * @date 2018/12/16
 * @description BaseRequest
 */
public abstract class BaseRequest<R extends BaseRequest> {

    protected String url;
    protected int requestCode;
    protected HttpHeaders headers = new HttpHeaders();
    protected HttpParams params = new HttpParams();

    public BaseRequest(String url,int requestCode) {
        this.url = url;
        this.requestCode = requestCode;
        EasyHttp config = EasyHttp.getInstance();
        //添加公共请求参数
        if (config.getCommonParams() != null) {
            params.put(config.getCommonParams());
        }
        if (config.getCommonHeaders() != null) {
            headers.put(config.getCommonHeaders());
        }
    }

    /**
     * 添加头信息
     */
    public R headers(HttpHeaders headers) {
        this.headers.put(headers);
        return (R) this;
    }

    /**
     * 添加头信息
     */
    public R headers(String key, String value) {
        headers.put(key, value);
        return (R) this;
    }

    /**
     * 移除头信息
     */
    public R removeHeader(String key) {
        headers.remove(key);
        return (R) this;
    }

    /**
     * 移除所有头信息
     */
    public R removeAllHeaders() {
        headers.clear();
        return (R) this;
    }

    /**
     * 设置参数
     */
    public R params(HttpParams params) {
        this.params.put(params);
        return (R) this;
    }

    public R params(String key, String value) {
        params.put(key, value);
        return (R) this;
    }

    public R removeParam(String key) {
        params.remove(key);
        return (R) this;
    }

    public R removeAllParams() {
        params.clear();
        return (R) this;
    }

    class CommonRequestCallBack implements Callback.CommonCallback<String>, RequestInterceptListener {
        private ICallBack callBack;
        private int requestCode;

        CommonRequestCallBack( int requestCode, ICallBack callBack) {
            super();
            this.callBack = callBack;
            this.requestCode = requestCode;
        }

        @Override
        public void onSuccess(String result) {
            callBack.onLoadSuccess(requestCode, result);

            DbCookieStore instance = DbCookieStore.INSTANCE;
            List<HttpCookie> cookies = instance.getCookies();
            callBack.onLoadCookies(cookies);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            ex.printStackTrace();
            callBack.onLoadFailure(requestCode, ex.toString());

        }

        @Override
        public void onCancelled(CancelledException cex) {   // 请求被取消
            cex.printStackTrace();
            callBack.onLoadCancel(requestCode);

        }

        @Override
        public void onFinished() {  // 请求结束
        }

        @Override
        public void beforeRequest(UriRequest request) throws Throwable {
            LogUtil.e("url= " + request.getRequestUri().toString());
            LogUtil.e("param= " + request.getParams().toString());
        }

        @Override
        public void afterRequest(UriRequest request) throws Throwable {
            callBack.onLoadHeaders(requestCode, request.getResponseHeaders());


        }
    }
}
