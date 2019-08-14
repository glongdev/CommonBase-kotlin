package com.dev.common.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

/**
 * @author guolong
 * @since 2019/4/3
 */
abstract class BaseFragment<T : ViewModel> : Fragment() {
    protected val mTag = javaClass.simpleName
    protected var mContentView: View? = null

    protected var mViewModel: T? = null

    /**
     * 设置content layout id
     */
    protected abstract val contentLayoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initViewModel()
        val bundle = arguments
        initArguments(bundle ?: return)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContentView = inflater.inflate(contentLayoutId, container, false)
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribe()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    /**
     * 初始化Activity传递的参数
     */
    protected open fun initArguments(bundle: Bundle) {

    }

    /**
     * 初始化ViewModel，通过获取当前类的泛型，然后生成ViewModel
     *
     * @return 指定泛型的ViewModel
     */
    protected open fun initViewModel(): T? {
        val superTYpe = javaClass.genericSuperclass as? ParameterizedType ?: return null
        val types = superTYpe.actualTypeArguments
        if (types.isNotEmpty()) {
            val type = types[0]
            return ViewModelProviders.of(activity ?: return null).get(type as Class<T>)
        }
        return null
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
            if (activity == null || activity!!.isFinishing) {
                return
            }
            val view = activity?.currentFocus ?: return
            val binder = view.windowToken
            val manager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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
            if (activity == null || activity!!.isFinishing) {
                return
            }
            editText?.requestFocus() ?: return
            val manager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.showSoftInput(editText, 0)
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
