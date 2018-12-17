package com.hfs.easyandroid.net;

import android.content.Context;
import android.text.TextUtils;

import com.hfs.easyandroid.App;
import com.hfs.easyhttp.EasyHttp;
import com.hfs.easyhttp.listener.CommonCallBack;
import com.hfs.easyhttp.model.HttpHeaders;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
 * @author HuangFusheng
 * @date 2018/12/17
 * @description JSHCallBack
 */
public abstract class JSHCallBack extends CommonCallBack {

    @Override
    public void onLoadHeaders(int requestCode, Map<String, List<String>> headers) {
        super.onLoadHeaders(requestCode, headers);
    }

    @Override
    public void onLoadCookies(List<HttpCookie> cookies) {
        super.onLoadCookies(cookies);

        storeCookies(App.gContext, cookies);

    }

    private void storeCookies(Context context, List<HttpCookie> cookies) {

        for (HttpCookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if ("JSESSIONID".equals(name)) {
                if (!TextUtils.isEmpty(value)) {
                    if (value.contains("ylh")) {
                        Configurations.setJSESSIONID(context, value);
                    }
                }
            }
            if ("jsh-remember-me".equals(name)) {
                Configurations.setJSH_REMEBER_ME(context, value);
            }
            if ("token".equals(name) || "access_token".equals(name)) {
                Configurations.setToken(context, value);
            }
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", "bearer " + Configurations.getToken(context));
        httpHeaders.put("Cookie", "JSESSIONID=" + Configurations.getJSESSIONID(context));
        EasyHttp.getInstance().removeAllHeaders();
        EasyHttp.getInstance().addCommonHeaders(httpHeaders);
    }
}
