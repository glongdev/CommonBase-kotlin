package com.dev.common.scheduler.task

/**
 * 任意线程切换到UI线程的Task
 * 泛型T代表返回值
 * @author guolong
 * @since 2019/7/7
 */
abstract class UITask<T> : ITask<T>() {
    abstract fun doOnUIThread(): T
}
