package com.hfs.easyandroid.http;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author HuangFusheng
 * @date 2018/12/17
 * @description Configurations
 */
public class Configurations {
    /**
     * SharedPreference保存到config.xml中
     */
    private static final String SP_FILE = "config";

    /**
     * 登录JSESSIONID
     */
    private static final String JSESSIONID = "JSESSIONID";

    /**
     * 登录JSESSIONID
     */

    private static final String JSH_REMEBER_ME = "jsh_remember_me";

    /**
     * token
     */
    private static final String TOKEN = "token";
    /**
     * id
     */
    private static final String ID = "id";
    /**
     * userName
     */
    private static final String USERNAME = "userName";
    /**
     * memberId
     */
    private static final String MEMBERID = "memberId";

    /**
     * 版本号
     */
    private static final String VERSION_CODE = "version_code";
    /**
     * 账号
     */
    private static final String USER = "user";
    /**
     * 密码
     */
    private static final String PASSWORD = "password";

    public static String getJSESSIONID(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(JSESSIONID, "");
    }

    public static void setJSESSIONID(Context context, String uuid) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(JSESSIONID, uuid);
        editor.apply();
    }

    public static String getJSH_REMEBER_ME(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(JSH_REMEBER_ME, "");
    }

    public static void setJSH_REMEBER_ME(Context context, String uuid) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(JSH_REMEBER_ME, uuid);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(TOKEN, "");
    }

    public static void setToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static String getId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(ID, "");
    }

    public static void setId(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(ID, token);
        editor.apply();
    }

    public static String getUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(USERNAME, "");
    }

    public static void setUserName(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USERNAME, token);
        editor.apply();
    }

    public static String getMemberId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(MEMBERID, "");
    }

    public static void setMemberId(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(MEMBERID, token);
        editor.apply();
    }

    public static void setVersionCode(Context context, int versionCode) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(VERSION_CODE, versionCode);
        editor.apply();
    }

    public static int getVersionCode(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getInt(VERSION_CODE, 0);
    }

    public static String getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(USER, "");
    }

    public static void setUser(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER, token);
        editor.apply();
    }

    public static String getPassWord(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        return sp.getString(PASSWORD, "");
    }

    public static void setPassWord(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PASSWORD, token);
        editor.apply();
    }

    public static void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
