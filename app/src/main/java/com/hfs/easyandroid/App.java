package com.hfs.easyandroid;

import android.app.Application;

import com.hfs.easyhttp.EasyHttp;
import com.hfs.easyimageloader.loader.GlideLoader;
import com.hfs.easyimageloader.EasyImageLoader;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description App
 */
public class App extends Application {

    public static App gContext;

    @Override
    public void onCreate() {
        super.onCreate();
        gContext = this;
        //初始化图片库
        EasyImageLoader.getInstance().init(this,new GlideLoader());

        EasyHttp.getInstance().init(this);

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        EasyImageLoader.getInstance().onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        EasyImageLoader.getInstance().onLowMemory();

    }
}
