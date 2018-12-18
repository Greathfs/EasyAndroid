package com.hfs.easy.databus;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description Event
 */
public class Event<T> {
    private int code;
    private T data;


    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

