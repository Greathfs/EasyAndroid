package com.hfs.easyhttp.listener;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
 * @author HuangFusheng
 * @date 2018/12/17
 * @description CommonCallBack
 */
public abstract class CommonCallBack implements ICallBack {
    @Override
    public void onLoadHeaders(int requestCode, Map<String, List<String>> headers) {

    }

    @Override
    public void onLoadCancel(int requestCode) {

    }

    @Override
    public void onLoadCookies(List<HttpCookie> cookies) {

    }
}
