package com.hfs.easyandroid.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hfs.easy.image.EasyImageLoader;
import com.hfs.easyandroid.R;

/**
 * @author HuangFusheng
 * @date 2018/12/17
 * @description ImageActivity
 */
public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        ImageView img1 = (ImageView) findViewById(R.id.img_1);

        String url = "https://static-ali.ihansen.org/app/bg1440/pJqfhKUpCh8.jpg";
        EasyImageLoader.getInstance()
                .load(url)
                .imageRadiusSize(20)
                .override(100, 100)
                .rotate(45)
                .placeholder(R.mipmap.ic_launcher)
                .isSkipMemoryCache(true)
                .into(img1);
    }

    public void next(View view) {
        startActivity(new Intent(this, ListActivity.class));
    }
}
