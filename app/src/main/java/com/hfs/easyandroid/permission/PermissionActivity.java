package com.hfs.easyandroid.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hfs.easy.callback.PermissionCallBack;
import com.hfs.easy.utils.PermissionUtil;
import com.hfs.easyandroid.R;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description PermissionActivity
 */
public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_permission);
    }

    public void requestCamera(View view) {
        PermissionUtil.requestPermission(this, Manifest.permission.CAMERA, new PermissionCallBack() {
            @Override
            public void allowPermission() {
                Toast.makeText(PermissionActivity.this, "获取到权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void notAllowPermission(String... permissions) {
                PermissionUtil.setupPermission(PermissionActivity.this,"相机","相机功能");
            }
        });
    }
}
