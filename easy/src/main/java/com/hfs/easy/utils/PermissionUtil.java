package com.hfs.easy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;

import com.hfs.easy.R;
import com.hfs.easy.callback.PermissionCallBack;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Locale;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description PermissionUtil
 */
public class PermissionUtil {
    public static final int SETTING_REQUEST_CODE = 1000;


    /**
     * 检查是否有权限
     *
     * @param context
     * @param permission
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasPermission(Context context, String permission) {
        return context.checkSelfPermission(permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity activity, String[] permission, PermissionCallBack callBack) {
        requestPermission(activity,callBack,permission);
    }

    public static void requestPermission(Activity activity, String permission, PermissionCallBack callBack) {
        requestPermission(activity,callBack,permission);
    }


    private static void requestPermission(Activity activity, final PermissionCallBack permissionCallBack, final String... permissions) {
        boolean hasPermission = true;
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean granted) throws Exception {
                        if (granted) {
                            permissionCallBack.allowPermission();
                        } else {
                            permissionCallBack.notAllowPermission(permissions);
                        }
                    }
                });

    }


    public static void setupPermission(final Activity activity, String permissionName, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle("权限申请")
                .setMessage(String.format(Locale.getDefault(),
                        "请在“权限”中开启“%1s权限”，以正常使用%2s", permissionName, msg))
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isMiUi()) {
                            setMiUiPermissions(activity);
                        } else {
                            startAppSettings(activity);
                        }
                    }
                });

        builder.show();

    }

    private static void setMiUiPermissions(Activity activity) {
        if (isMiUi()) {
            try {
                // MIUI 8
                activity.startActivityForResult(new Intent("miui.intent.action.APP_PERM_EDITOR")
                        .setClassName("com.miui.securitycenter",
                                "com.miui.permcenter.permissions.PermissionsEditorActivity")
                        .putExtra("extra_pkgname", activity.getPackageName()), SETTING_REQUEST_CODE);
            } catch (Exception e) {
                try {
                    // MIUI 5/6/7
                    activity.startActivityForResult(new Intent("miui.intent.action.APP_PERM_EDITOR")
                            .setClassName("com.miui.securitycenter",
                                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
                            .putExtra("extra_pkgname", activity.getPackageName()), SETTING_REQUEST_CODE);
                } catch (Exception e1){
                    // 否则跳转到应用详情
                    activity.startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.fromParts("package", activity.getPackageName(),
                                    null)), SETTING_REQUEST_CODE);
                }
            }
        }
    }


    private static boolean isMiUi() {
        return PlatformUtil.SYS_MIUI.equals(PlatformUtil.getSystem());
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private static void startAppSettings(Activity activity) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, SETTING_REQUEST_CODE);
    }

}
