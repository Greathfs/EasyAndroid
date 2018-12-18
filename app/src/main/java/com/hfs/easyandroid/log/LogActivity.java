package com.hfs.easyandroid.log;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hfs.easy.utils.LogUtil;
import com.hfs.easyandroid.App;
import com.hfs.easyandroid.R;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description LogActivity
 */
public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        LogUtil.v("hello");
        LogUtil.d("hello");
        LogUtil.i("hello");
        LogUtil.w("hello");
        LogUtil.e("hello");
        LogUtil.wtf("hello");
        LogUtil.json("{\"name\":\"hacket\",\"pass\":\"123456\"}");
        LogUtil.xml("<root><name>hacket</name><pass>123456</pass></root>");
        try {
            String s = null;
            s.toString();
        } catch (Exception e) {
            LogUtil.e("hello", e, e.getMessage());
            LogUtil.printStackTrace(e);
        }

    }
}
