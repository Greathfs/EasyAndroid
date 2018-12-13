package com.hfs.easyimageloader.listener;

import android.content.Context;

import com.hfs.easyimageloader.options.LoaderOptions;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description ILoaderStrategy
 */
public interface ILoaderStrategy {

    /**
     * 初始化
     * @param context
     */
    void init(Context context);

    /**
     * 加载图片
     * @param options
     */
    void loadImage(LoaderOptions options);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache();

    void onTrimMemory(int level);

    void onLowMemory();
}
