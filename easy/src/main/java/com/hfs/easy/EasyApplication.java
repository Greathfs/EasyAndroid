package com.hfs.easy;

import android.app.Application;

import com.hfs.easy.image.EasyImageLoader;
import com.hfs.easy.manager.AppInitManager;

/**
 * @author HuangFusheng
 * @date 2018/12/19
 * @description 基础Application,其他Application需要继承这个Application
 */
public class EasyApplication extends Application {

    public static EasyApplication gContext;

    @Override
    public void onCreate() {
        super.onCreate();

        gContext = this;

        AppInitManager.getInstance().initializeApp(this);
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
