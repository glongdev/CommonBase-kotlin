package com.dev.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * 主要封装屏幕适配，屏幕相关参数
 * 在Application中初始化后,所有的成员均可用
 *
 * @author guolong
 * @since 2019/4/3
 */
public class ScreenAdaptiveUtil {
    /**
     * 状态栏高度（px)
     */
    public static int sStatusBarHeight;

    /**
     * 屏幕宽度（px）
     */
    public static int sScreenWidth;

    /**
     * 屏幕高度（px）
     */
    public static int sScreenHeight;

    /**
     * 当前App的density值
     * px = dp * density {@link android.util.TypedValue#applyDimension}
     */
    public static float sDensity;

    /**
     * 当前App的scaleDensity值（一般与Density值相同，修改系统字体后与Density值不同）
     * px = sp * scaleDensity {@link android.util.TypedValue#applyDimension}
     */
    public static float sScaleDensity;

    /**
     * 当前App的DensityDpi
     */
    public static int sDensityDpi;

    /**
     * 设计图(design_width)的宽度，单位是dp
     */
    public static final float DESIGN_WIDTH = 360f;//360f对应1920*1080尺寸宽度的dp值

    //原始的几个值
    private static float oriDensity;
    private static float oriScaleDensity;

    /**
     * 初始系统参数
     *
     * @param application Application
     */
    public static void adaptive(final Application application) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        sStatusBarHeight = getStatusBarHeight(application);
        if (oriDensity == 0) {
            sScreenWidth = displayMetrics.widthPixels;
            sScreenHeight = displayMetrics.heightPixels;

            // 初始化
            oriDensity = sDensity = displayMetrics.density;
            oriScaleDensity = sScaleDensity = displayMetrics.scaledDensity;
            sDensityDpi = displayMetrics.densityDpi;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        oriScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
    }

    public static void adaptive(Activity activity) {
        // 修改--
        sDensity = sScreenWidth / DESIGN_WIDTH;
        sScaleDensity = sDensity * (oriScaleDensity / oriDensity);
        sDensityDpi = (int) (sScaleDensity * 160);

        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        displayMetrics.density = sDensity;
        displayMetrics.scaledDensity = sScaleDensity;
        displayMetrics.densityDpi = sDensityDpi;
    }

    /**
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
