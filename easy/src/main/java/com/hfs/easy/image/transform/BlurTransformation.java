package com.hfs.easy.image.transform;

import android.graphics.Bitmap;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.hfs.easy.image.utils.BitmapUtil;

import java.security.MessageDigest;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description 高斯模糊转换
 */

public class BlurTransformation extends BitmapTransformation {
    private static final String ID = BlurTransformation.class.getName();
    private static final byte[] ID_BYTES = ID.getBytes(Key.CHARSET);

    private int defaultRadius=15;
    public BlurTransformation(@IntRange(from=0) int radius){
        defaultRadius=radius;
    }
    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);

    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return  BitmapUtil.fastBlur(toTransform,defaultRadius);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BlurTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
