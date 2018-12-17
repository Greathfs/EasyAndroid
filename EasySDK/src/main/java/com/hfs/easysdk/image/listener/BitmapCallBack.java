package com.hfs.easysdk.image.listener;

import android.graphics.Bitmap;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description BitmapCallBack
 */
public interface BitmapCallBack {

    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Exception e);

    public static class EmptyCallback implements BitmapCallBack {


        @Override
        public void onBitmapLoaded(Bitmap bitmap) {

        }

        @Override
        public void onBitmapFailed(Exception e) {

        }
    }
}
