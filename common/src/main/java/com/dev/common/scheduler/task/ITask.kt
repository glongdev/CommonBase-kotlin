package com.dev.common.scheduler.task

/**
 * @author guolong
 * @since 2019/7/7
 */
abstract class ITask<T> {

    /**
     * 运行在主线程
     */
    open fun onComplete(t: T) {}

    /**
     * 运行在主线程
     */
    open fun onFail(e: Throwable) {}
}
