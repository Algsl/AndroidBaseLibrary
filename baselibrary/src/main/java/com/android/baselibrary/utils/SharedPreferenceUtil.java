package com.android.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.baselibrary.ui.BaseApplication;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceUtil {
    public static String PREFERENCE_NAMW= BaseApplication.getApp().getPackageName();

    /**
     * 存储字符串
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putString(Context context, String key, String value){
        SharedPreferences preferences=context.getSharedPreferences(PREFERENCE_NAMW,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,value);
        return editor.commit();
    }

    public static String getString(Context context,String key){
        return getString(context,key,"");
    }

    /**
     * 读取字符串
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Context context,String key,String defaultValue){
        SharedPreferences preferences=context.getSharedPreferences(PREFERENCE_NAMW,Context.MODE_PRIVATE);
        return preferences.getString(key,defaultValue);
    }

    /**
     * 存储整型数字
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 读取整型数字
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }
    /**
     * 读取整型数字（带默认值的）
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        return preferences.getInt(key, defaultValue);
    }

    /**
     * 存储长整型数字
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }
    /**
     * 读取长整型数字
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, 0xffffffff);
    }
    /**
     * 读取长整型数字（带默认值的）
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        return preferences.getLong(key, defaultValue);
    }

    /**
     * 存储Float数字
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 读取Float数字
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, 0.0f);
    }

    /**
     * 读取Float数字（带默认值的）
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        return preferences.getFloat(key, defaultValue);
    }

    /**
     * 存储boolean类型数据
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 读取boolean类型数据
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 读取boolean类型数据（带默认值的）
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * 清除数据
     */
    public static boolean clearPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAMW, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        return editor.commit();
    }
}
