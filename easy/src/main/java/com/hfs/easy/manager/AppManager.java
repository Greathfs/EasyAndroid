package com.hfs.easy.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * @author HuangFusheng
 * @date 2018/12/19
 * @description AppManager
 */
public class AppManager {

    private static Stack<Activity> aliveActivityStack;
    private static Stack<Activity> visibleActivityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addAliveActivity(Activity activity) {
        if (aliveActivityStack == null) {
            aliveActivityStack = new Stack<>();
        }
        aliveActivityStack.add(activity);
    }

    /**
     * 添加前台可见Activity到堆栈
     */
    public void addFrontActivity(Activity activity) {
        if (visibleActivityStack == null) {
            visibleActivityStack = new Stack<>();
        }
        visibleActivityStack.add(activity);
    }



    public void removeAliveActivity(Activity activity) {
        if (aliveActivityStack == null) {
            aliveActivityStack = new Stack<>();
        }
        aliveActivityStack.remove(activity);
    }

    public void removeFrontActivity(Activity activity) {
        if (visibleActivityStack == null) {
            visibleActivityStack = new Stack<>();
        }
        visibleActivityStack.remove(activity);
    }



    /**
     * 获取当前可见Activity，如果没有，获取当前还未销毁的Activity
     */
    public Activity currentVisibleActivity() {
        if (visibleActivityStack.size() > 0) {
            return visibleActivityStack.lastElement();
        }
        return currentAliveActivity();
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentAliveActivity() {
        if (aliveActivityStack.size() > 0) {
            return aliveActivityStack.lastElement();
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = aliveActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            aliveActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : aliveActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
//        for (int i = 0, size = aliveActivityStack.size(); i < size; i++) {
//            if (null != aliveActivityStack.get(i)) {
//                aliveActivityStack.get(i).finish();
//            }
//        }
        while (!aliveActivityStack.empty()) {
            if (null != aliveActivityStack.get(0)) {
                aliveActivityStack.get(0).finish();
                aliveActivityStack.remove(0);
            }
        }
//        aliveActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
