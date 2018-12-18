package com.hfs.easy.http.request;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hfs.easy.http.listener.ICallBack;
import com.hfs.easy.http.type.PostType;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * @author HuangFusheng
 * @date 2018/12/16
 * @description PostRequest
 */
public class PostRequest extends BaseBodyRequest<PostRequest> {

    private PostType mPostType;

    public PostRequest(String url, int requestCode, PostType type) {
        super(url, requestCode);
        mPostType = type;
    }

    public void execute(ICallBack callBack) {
        RequestParams requestParams = new RequestParams(url);

        if (headers.headersMap.size() > 0) {
            for (Map.Entry<String, String> entry : headers.headersMap.entrySet()) {
                requestParams.addHeader(entry.getKey(), entry.getValue());
            }
        }

        switch (mPostType) {
            case NULL:
                if (null != params && !params.urlParamsMap.isEmpty()) {
                    for (Map.Entry<String, String> entry : params.urlParamsMap.entrySet()) {
                        requestParams.addBodyParameter(entry.getKey(), entry.getValue());
                    }
                }
                requestParams.addHeader("content-type", "application/json");
                break;
            case WITH_FORM:
                if (null != params && !params.urlParamsMap.isEmpty()) {
                    for (Map.Entry<String, String> entry : params.urlParamsMap.entrySet()) {
                        requestParams.addBodyParameter(entry.getKey(), entry.getValue());
                    }
                }
                requestParams.setMultipart(true);
                requestParams.setCharset("UTF-8");
                break;
            case WITH_JSON:
                requestParams.setAsJsonContent(true);
                if (object == null && TextUtils.isEmpty(json)) {
                    //服务器需要传参的json对象
                    JSONObject jsonObject = new JSONObject();
                    if (null != params && !params.urlParamsMap.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.urlParamsMap.entrySet()) {
                            try {
                                jsonObject.put(entry.getKey(), entry.getValue());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        requestParams.setBodyContent(jsonObject.toString().replace("\\", ""));
                    }
                }

                if (object != null) {
                    String json = new Gson().toJson(object);
                    requestParams.setBodyContent(json.replace("\\", ""));
                }

                if (!TextUtils.isEmpty(json)) {
                    requestParams.setBodyContent(json.replace("\\", ""));

                }

                break;
            default:
                break;
        }

        x.http().post(requestParams, new CommonRequestCallBack(requestCode, callBack));
    }
}
