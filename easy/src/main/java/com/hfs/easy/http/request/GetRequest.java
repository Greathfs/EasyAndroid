package com.hfs.easy.http.request;

import com.hfs.easy.http.listener.ICallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * @author HuangFusheng
 * @date 2018/12/16
 * @description GetRequest
 */
public class GetRequest extends BaseRequest<GetRequest> {
    public GetRequest(String url,int requestCode) {
        super(url,requestCode);
    }

    public void execute(ICallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        if (null != params && !params.urlParamsMap.isEmpty()) {
            for (Map.Entry<String, String> entry : params.urlParamsMap.entrySet()) {
                requestParams.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }

        if (headers.headersMap.size() > 0) {
            for (Map.Entry<String, String> entry : headers.headersMap.entrySet()) {
                requestParams.addHeader(entry.getKey(), entry.getValue());
            }
        }

         x.http().get(requestParams,new CommonRequestCallBack(requestCode,callBack));
    }
}
