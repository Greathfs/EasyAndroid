package com.hfs.easy.http.request;

/**
 * @author HuangFusheng
 * @date 2018/12/16
 * @description BaseBodyRequest
 */
public abstract class BaseBodyRequest<R extends BaseBodyRequest> extends BaseRequest<R> {

    //上传的Json
    protected String json;
    //上传的对象
    protected Object object;


    public BaseBodyRequest(String url, int requestCode) {
        super(url, requestCode);
    }

    public R withObject(Object object) {
        this.object = object;
        return (R) this;
    }

    public R withJson(String json) {
        this.json = json;
        return (R) this;
    }
}
