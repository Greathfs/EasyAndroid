package com.hfs.easyhttp.request;

import android.text.TextUtils;

import com.hfs.easyhttp.listener.ICallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author HuangFusheng
 * @date 2018/12/17
 * @description UploadRequest
 */
public class UploadRequest extends BaseRequest<PostRequest> {

    private String name;
    private boolean mUseFileName;
    private List<File> mFiles;

    public UploadRequest(String url, int requestCode, List<File> files) {
        super(url, requestCode);
        mFiles = files;
    }

    /**
     * 指定传参
     * @param name
     * @return
     */
    public UploadRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 是否使用文件本身的名字
     * @param useFileName 文件名字
     * @return
     */
    public UploadRequest useFileName(boolean useFileName) {
        mUseFileName = useFileName;
        return this;
    }


    public void execute(ICallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        if (null != params && !params.urlParamsMap.isEmpty()) {
            for (Map.Entry<String, String> entry : params.urlParamsMap.entrySet()) {
                requestParams.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }

        if (headers.headersMap.size() > 0) {
            for (Map.Entry<String, String> entry : headers.headersMap.entrySet()) {
                requestParams.addHeader(entry.getKey(), entry.getValue());
            }
        }

        requestParams.setMultipart(true);
        requestParams.setCharset("UTF-8");

        if (TextUtils.isEmpty(name)) {
            if (mFiles.size() == 1) {
                name = "file";
            }else {
                name = "files";
            }
        }

        for (int i = 0; i < mFiles.size(); i++) {
            File file = mFiles.get(i);
            if (mUseFileName) {
                requestParams.addBodyParameter(file.getName(), file, null);
            }else {
                requestParams.addBodyParameter(name, file, null);
            }

        }

        x.http().post(requestParams,new CommonRequestCallBack(requestCode,callBack));
    }
}
