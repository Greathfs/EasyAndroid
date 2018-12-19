package com.hfs.easy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

import com.hfs.easy.databus.BindEventBus;
import com.hfs.easy.databus.EventBusHelper;
import com.hfs.easy.utils.ViewLayoutUtil;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

    }

    @Override
    public void onStart() {
        super.onStart();
        // 若使用BindEventBus注解，则绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusHelper.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 若使用BindEventBus注解，则解绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusHelper.unregister(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        hideSoftKeyboard();
        ViewLayoutUtil.fixInputMethodManagerLeak(this);
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 获取setContentView布局
     * @return
     */
    public abstract int getContentViewId();

    public abstract void initView();

    public abstract void initData();

}
