package com.hfs.easy.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hfs.easy.manager.AppManager;

/**
 * @author HuangFusheng
 * @date 2018/12/19
 * @description 管理生命周期回调
 */
public class EasyActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks  {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppManager.getAppManager().addAliveActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        AppManager.getAppManager().addFrontActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        AppManager.getAppManager().removeFrontActivity(activity);

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppManager.getAppManager().removeAliveActivity(activity);
    }
}
