package com.hfs.easy.manager;

import android.app.Application;

import com.hfs.easy.app.EasyActivityLifecycleCallback;
import com.hfs.easy.http.EasyHttp;
import com.hfs.easy.image.EasyImageLoader;

/**
 * @author HuangFusheng
 * @date 2018/12/19
 * @description AppInitManager
 */
public class AppInitManager {

    private static AppInitManager sInstance;

    /**
     * 是否初始化
     */
    private boolean mIsInitialized;

    public static AppInitManager getInstance() {
        if (sInstance == null) {
            sInstance = new AppInitManager();
        }
        return sInstance;
    }

    public static void destroy() {
        if (null != sInstance) {
            sInstance = null;
        }
    }

    /**
     * 初始化数据
     * @param context
     */
    public void initializeApp(Application context) {
        if (mIsInitialized) {
            return;
        }
        //标志位
        mIsInitialized = true;
        //管理Activity生命周期
        context.registerActivityLifecycleCallbacks(new EasyActivityLifecycleCallback());
        //初始化图片库
        EasyImageLoader.getInstance().init(context);
        //初始化EasyHttp
        EasyHttp.getInstance().init(context);


    }

}
