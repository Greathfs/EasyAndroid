package com.hfs.easyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hfs.easyimageloader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img1 = (ImageView) findViewById(R.id.img_1);

        String url = "https://static-ali.ihansen.org/app/bg1440/pJqfhKUpCh8.jpg";
        ImageLoader.getInstance()
                .load(url)
                .imageRadiusSize(20)
                .override(100,100)
                .rotate(45)
                .placeholder(R.mipmap.ic_launcher)
                .isSkipMemoryCache(true)
                .into(img1);

    }

    public void next(View view) {
        startActivity(new Intent(this,ListActivity.class));
    }
}
