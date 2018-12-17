package com.hfs.easysdk.image.options;

import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.view.View;

import com.hfs.easysdk.image.ImageLoader;
import com.hfs.easysdk.image.listener.BitmapCallBack;
import com.hfs.easysdk.image.listener.ILoaderStrategy;

import java.io.File;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description 该类为图片加载框架的通用属性封装，不能耦合任何一方的框架
 */
public class LoaderOptions {

    /**
     * 图片地址url
     */
    public String url;
    /**
     * 文件格式
     */
    public File file;
    /**
     * drawable格式
     */
    public int drawableResId;
    /**
     * uri格式
     */
    public Uri uri;
    /**
     * 占位图
     */
    public int placeholderResId;
    /**
     * 错误图
     */
    public int errorResId;
    /**
     * 是否作为gif展示
     */
    public boolean asGif = false;
    /**
     * 是否渐变平滑的显示图片,默认为true
     */
    public boolean isCrossFade = true;
    /**
     * 是否跳过内存缓存
     */
    public boolean isSkipMemoryCache = false;
    /**
     * 磁盘缓存策略
     */
    public DiskCacheStrategy mDiskCacheStrategy = DiskCacheStrategy.DEFAULT;
    /**
     * 是否使用高斯模糊
     */
    public boolean blurImage = false;
    /**
     * 高斯模糊参数，越大越模糊
     */
    public int blurValue;
    /**
     * 圆角角度
     */
    public int imageRadius = 0;
    /**
     * 是否圆图
     */
    public boolean isCircle = false;
    /**
     * 指定宽度
     */
    public int targetWidth;
    /**
     * 指定高度
     */
    public int targetHeight;
    /**
     * 旋转角度 默认不旋转
     */
    public float degrees;
    public View targetView;
    public BitmapCallBack callBack;

    public ILoaderStrategy loader;

    public LoaderOptions(String url) {
        this.url = url;
    }

    public LoaderOptions(File file) {
        this.file = file;
    }

    public LoaderOptions(@DrawableRes int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public LoaderOptions(Uri uri) {
        this.uri = uri;
    }

    public void into(View targetView) {
        this.targetView = targetView;
        ImageLoader.getInstance().loadOptions(this);
    }

    public void bitmap(BitmapCallBack callBack) {
        this.callBack = callBack;
        ImageLoader.getInstance().loadOptions(this);
    }

    public LoaderOptions loader(ILoaderStrategy imageLoader) {
        this.loader = imageLoader;
        return this;
    }

    public LoaderOptions placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoaderOptions error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }


    public LoaderOptions isSkipMemoryCache(boolean isSkipMemoryCache) {
        this.isSkipMemoryCache = isSkipMemoryCache;
        return this;

    }

    public LoaderOptions imageRadiusSize(int imageRadius) {
        this.imageRadius = imageRadius;

        return this;
    }

    public LoaderOptions isCrossFade(boolean isCrossFade) {
        this.isCrossFade = isCrossFade;
        return this;
    }

    public LoaderOptions isBlur(boolean blurImage) {
        this.blurImage = blurImage;
        return this;
    }
    public LoaderOptions blurValue(@IntRange(from = 0) int blurvalue) {
        this.blurValue = blurvalue;
        return this;
    }

    public LoaderOptions isCircle(boolean isCircle) {
        this.isCircle = isCircle;
        return this;
    }

    public LoaderOptions override(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    public LoaderOptions asGif(boolean asGif) {
        this.asGif = asGif;
        return this;
    }

    public LoaderOptions rotate(float degrees) {
        this.degrees = degrees;
        return this;
    }

    public LoaderOptions diskCacheStrategy(DiskCacheStrategy mDiskCacheStrategy) {
        this.mDiskCacheStrategy = mDiskCacheStrategy;
        return this;

    }

    //对应磁盘缓存策略
    public enum DiskCacheStrategy {
        All, NONE, SOURCE, RESULT, DEFAULT
    }

}
