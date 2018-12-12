package com.hfs.easyandroid;

import android.app.Application;

import com.hfs.easyimageloader.loader.GlideLoader;
import com.hfs.easyimageloader.ImageLoader;

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
        ImageLoader.getInstance().init(this,new GlideLoader());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getInstance().onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getInstance().onLowMemory();

    }
}
