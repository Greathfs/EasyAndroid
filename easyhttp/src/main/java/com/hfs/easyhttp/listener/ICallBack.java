package com.hfs.easyhttp.listener;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
 * @author HuangFusheng
 * @date 2018/12/15
 * @description ICallBack
 */
public interface ICallBack {

    void onLoadSuccess(int requestCode,String result);

    void onLoadFailure(int requestCode,String errorMsg);

    void onLoadCancel(int requestCode);

    void onLoadHeaders(int requestCode, Map<String, List<String>> headers);

    void onLoadCookies(List<HttpCookie> cookies);
}
