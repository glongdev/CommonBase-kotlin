package com.dev.common.scheduler.task

/**
 * 从任意线程切换的io线程的task
 * 泛型T代表返回值
 *
 * @author guolong
 * @since 2019/7/7
 */
abstract class IOTask<T> : ITask<T>() {
    abstract fun doOnIoThread(): T
}
