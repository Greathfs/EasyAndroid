package com.hfs.easy.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfs.easy.databus.BindEventBus;
import com.hfs.easy.databus.EventBusHelper;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description BaseFragment
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    private boolean isInitView = false;
    private boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initView();
        isInitView = true;
        isCanLoadData();
        return rootView;
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
        if(isVisibleToUser){
            isVisible = true;
            isCanLoadData();
        }else{
            isVisible = false;
        }
    }

    private void isCanLoadData(){
        //所以条件是view初始化完成并且对用户可见
        if(isInitView && isVisible ){
            lazyLoad();

            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }


    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void initView();

    /**
     * 加载要显示的数据
     */
    protected abstract void lazyLoad();
}
