package com.dev.common.utils;

import android.util.Log;

/**
 * Log工具
 *
 * @author guolong
 * @since 2019/4/24
 */
public class ZDLog {

    public static void d(String TAG, String msg) {
        if (AppUtils.isAppDebug()) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String TAG, String msg) {
        Log.i(TAG, msg);
    }

    public static void w(String TAG, String msg) {
        Log.w(TAG, msg);
    }

    public static void e(String TAG, String msg) {
        Log.e(TAG, msg);
    }

    public static void e(String TAG, String msg, Throwable t) {
        Log.e(TAG, msg, t);
    }
}
