package com.hfs.easyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hfs.easyandroid.image.ImageActivity;
import com.hfs.easyandroid.http.NetActivity;
import com.hfs.easyandroid.permission.PermissionActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void toImg(View view) {
        startActivity(new Intent(this,ImageActivity.class));
    }

    public void toNet(View view) {
        startActivity(new Intent(this,NetActivity.class));
    }

    public void toPermission(View view) {
        startActivity(new Intent(this,PermissionActivity.class));
    }
}
