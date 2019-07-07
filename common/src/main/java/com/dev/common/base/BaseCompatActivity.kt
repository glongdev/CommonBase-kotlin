package com.dev.common.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dev.common.utils.ScreenAdaptiveUtil
import java.lang.reflect.ParameterizedType

/**
 * @author guolong
 * @since 2019/4/3
 */

// 20分钟 展示一次 收益太少了！！
const val RE_SHOW_AD_TIME = 10 * 60 * 1000//退出后台多久后重新展示广告？

abstract class BaseCompatActivity<T : ViewModel> : AppCompatActivity() {

    protected var mTag = javaClass.simpleName
    protected var mViewModel: T? = null

    /**
     * 设置content layout id
     */
    protected abstract val contentLayoutId: Int

    /**
     * 初始化ViewModel，通过获取当前类的泛型，然后生成ViewModel
     *
     * @return 指定泛型的ViewModel
     */
    protected val viewModel: T?
        get() {
            val superTYpe = javaClass.genericSuperclass as ParameterizedType ?: return null
            val types = superTYpe.actualTypeArguments
            if (types.isNotEmpty()) {
                val type = types[0]
                return ViewModelProviders.of(this).get(type as Class<T>)
            }
            return null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(mTag, "onCreate()####")
        initWindow()
        initScreenAdaptive()
        mViewModel = viewModel
        setContentView(contentLayoutId)
        subscribe()
        initData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(mTag, "onDestroy()####")
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setupViews()
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        setupViews()
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        super.setContentView(view, params)
        setupViews()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    protected open fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar ?: return
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * 初始化窗口，沉侵式状态栏
     */
    protected open fun initWindow() {
        val flag: Int = if (Build.VERSION.SDK_INT >= 23) {
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        window.decorView.systemUiVisibility = flag
        initStatusBar()
    }

    protected open fun initStatusBar() {
        //        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
    }

    /**
     * 初始化屏幕适配
     */
    protected fun initScreenAdaptive() {
        ScreenAdaptiveUtil.adaptive(this)
    }

    /**
     * 初始化Views
     */
    protected open fun setupViews() {}

    /**
     * 订阅ViewModel的数据——>更新UI
     */
    protected fun subscribe() {}

    /**
     * 初始化数据
     */
    protected open fun initData() {}

    /**
     * 隐藏软键盘。
     */
    protected fun hideSoftKeyboard() {
        try {
            val view = currentFocus ?: return
            val binder = view.windowToken
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            Log.e(mTag, e.message, e)
        }

    }

    /**
     * 显示软键盘。
     */
    protected fun showSoftKeyboard(editText: EditText?) {
        try {
            if (editText != null) {
                editText.requestFocus()
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(editText, 0)
            }
        } catch (e: Exception) {
            Log.e(mTag, e.message, e)
        }
    }

    protected fun showLoading() {
        changeState(isLoading = true)
    }

    protected fun showContent() {
        changeState()
    }

    protected fun showEmpty() {
        changeState(isEmpty = true)
    }

    protected fun showError() {
        changeState(isError = true)
    }

    protected open fun changeState(isLoading: Boolean = false, isEmpty: Boolean = false, isError: Boolean = false) {

    }
}
