package com.hfs.easy.image;

import android.content.Context;
import android.net.Uri;

import com.hfs.easy.image.listener.ILoaderStrategy;
import com.hfs.easy.image.loader.GlideLoader;
import com.hfs.easy.image.options.LoaderOptions;

import java.io.File;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description 策略或者静态代理模式，开发者只需要关心ImageLoader + LoaderOptions
 */
public class EasyImageLoader {

    private static ILoaderStrategy sLoader;
    private static volatile EasyImageLoader sInstance;

    private EasyImageLoader() {
    }

    //单例模式
    public static EasyImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (EasyImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new EasyImageLoader();
                }
            }
        }
        return sInstance;
    }


    /**
     * 提供全局替换图片加载框架的接口，若切换其它框架，可以实现一键全局替换
     */
    public void init(Context context) {
        init(context,new GlideLoader());
    }

    /**
     * 提供全局替换图片加载框架的接口，若切换其它框架，可以实现一键全局替换
     */
    public void init(Context context,ILoaderStrategy loader) {
        loader.init(context);
        sLoader = loader;
    }

    public LoaderOptions load(String url) {
        return new LoaderOptions(url);
    }

    public LoaderOptions load(int drawable) {
        return new LoaderOptions(drawable);
    }

    public LoaderOptions load(File file) {
        return new LoaderOptions(file);
    }

    public LoaderOptions load(Uri uri) {
        return new LoaderOptions(uri);
    }

    /**
     * 优先使用实时设置的图片loader，其次使用全局设置的图片loader
     * @param options
     */
    public void loadOptions(LoaderOptions options) {
        if (options.loader != null) {
            options.loader.loadImage(options);
        } else {
            checkNotNull();
            sLoader.loadImage(options);
        }
    }

    public void clearMemoryCache() {
        checkNotNull();
        sLoader.clearMemoryCache();
    }

    public void clearDiskCache() {
        checkNotNull();
        sLoader.clearDiskCache();
    }

    public void onTrimMemory(int level) {
        checkNotNull();
        sLoader.onTrimMemory(level);
    }

    public void onLowMemory() {
        checkNotNull();
        sLoader.onLowMemory();
    }

    private void checkNotNull() {
        if (sLoader == null) {
            throw new NullPointerException("you must be set your imageLoader at first!");
        }
    }
}
