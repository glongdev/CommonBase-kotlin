package com.dev.common.scheduler

import android.annotation.SuppressLint
import com.dev.common.scheduler.task.IOTask
import com.dev.common.scheduler.task.UITask
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * 切换线程的执行类
 *
 * @author guolong
 * @since 2019/7/7
 */
@SuppressLint("CheckResult")
object RxScheduler {
    /**
     * 线程切换至 io线程 执行特定任务
     */
    fun <T> doOnIoThread(task: IOTask<T>) {
        Observable.just(task)
            .subscribeOn(Schedulers.io())
            .map { tioTask -> tioTask.doOnIoThread() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t -> task.onComplete(t) }, { throwable -> task.onFail(throwable) })
    }

    /**
     * 线程切换至UI线程执行特定任务
     */
    fun <T> doOnUIThread(task: UITask<T>) {
        Observable.just(task)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ tuiTask -> task.onComplete(tuiTask.doOnUIThread()) }, { throwable -> task.onFail(throwable) })
    }
}
