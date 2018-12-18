package com.hfs.easy.callback;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description PermissionCallBack
 */
public interface PermissionCallBack {

    void allowPermission();

    void notAllowPermission(String... permissions);
}
