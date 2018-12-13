package com.hfs.easyimageloader.loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hfs.easyimageloader.listener.ILoaderStrategy;
import com.hfs.easyimageloader.options.LoaderOptions;
import com.hfs.easyimageloader.transform.BlurTransformation;
import com.hfs.easyimageloader.transform.CircleTransformation;
import com.hfs.easyimageloader.transform.RotateTransformation;

import java.util.ArrayList;
import java.util.List;


/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description GlideLoader
 */
public class GlideLoader implements ILoaderStrategy {

    private Context mContext;

    @Override
    public void clearMemoryCache() {
        Glide.get(mContext).clearMemory();
    }

    @Override
    public void clearDiskCache() {
        Glide.get(mContext).clearDiskCache();
    }

    @Override
    public void onTrimMemory(int level) {
        Glide.get(mContext).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        Glide.get(mContext).onLowMemory();
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadImage(final LoaderOptions options) {
        RequestOptions requestOptions = new RequestOptions();

        if (options.errorResId != 0) {
            requestOptions.error(options.errorResId);
        }
        if (options.placeholderResId != 0) {
            requestOptions.placeholder(options.placeholderResId);
        }

        if (options.mDiskCacheStrategy != LoaderOptions.DiskCacheStrategy.DEFAULT) {
            if (LoaderOptions.DiskCacheStrategy.NONE == options.mDiskCacheStrategy) {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            } else if (LoaderOptions.DiskCacheStrategy.All == options.mDiskCacheStrategy) {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            } else if (LoaderOptions.DiskCacheStrategy.SOURCE == options.mDiskCacheStrategy) {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            } else if (LoaderOptions.DiskCacheStrategy.RESULT == options.mDiskCacheStrategy) {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
            }

        }

        if (options.isSkipMemoryCache) {
            requestOptions.skipMemoryCache(true);
        }
        if (options.targetHeight > 0 && options.targetWidth > 0) {
            requestOptions.override(options.targetWidth, options.targetHeight);
        }

        List<Transformation> list = new ArrayList<Transformation>();
        if (options.blurImage) {
            list.add(new BlurTransformation(options.blurValue));
        }
        if (options.imageRadius > 0) {
            list.add(new RoundedCorners(options.imageRadius));
        }
        if (options.isCircle) {
            list.add(new CircleTransformation());

        }
        if (options.degrees != 0) {
            list.add(new RotateTransformation(options.degrees));
        }
        if (list.size() > 0) {
            Transformation[] transformations = list.toArray(new Transformation[list.size()]);
            requestOptions.transforms(transformations);

        }

        RequestBuilder builder = getRequestBuilder(options);

        if (options.targetView instanceof ImageView) {
            builder.apply(requestOptions).into(((ImageView) options.targetView));
        }
    }


    /**
     * 获取getRequestBuilder
     *
     * @param options
     * @return
     */
    private RequestBuilder getRequestBuilder(LoaderOptions options) {
        RequestBuilder builder = null;
        if (options.asGif) {
            builder = Glide.with(mContext).asGif();
        }

        if (options.url != null) {
            builder = Glide.with(mContext).load(options.url);
        } else if (options.file != null) {
            builder = Glide.with(mContext).load(options.file);
        } else if (options.drawableResId != 0) {
            builder = Glide.with(mContext).load(options.drawableResId);
        } else if (options.uri != null) {
            builder = Glide.with(mContext).load(options.uri);
        }
        return builder;

    }


}
