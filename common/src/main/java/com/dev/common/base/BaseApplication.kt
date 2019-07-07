package com.dev.common.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.dev.common.utils.ScreenAdaptiveUtil
import com.dev.common.utils.Utils

/**
 * @author guolong
 * @since 2019/7/7
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        Utils.init(this)
        initScreenAdaptive()
    }

    /**
     * 屏幕适配
     */
    protected fun initScreenAdaptive() {
        ScreenAdaptiveUtil.adaptive(this)
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    companion object {

        /**
         * 展示在前台的activity数量
         */
        lateinit var app: Application
    }
}
