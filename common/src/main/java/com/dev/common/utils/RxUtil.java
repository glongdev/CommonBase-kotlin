package com.dev.common.utils;

import androidx.lifecycle.LifecycleOwner;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author guolong
 * @since 2019/4/18
 */
public class RxUtil {

    /**
     * background -> main
     */
    public static <T> Observable<T> backgroundToMain(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

//    /**
//     * background -> main 且绑定LifecycleOwner生命周期防止内存泄漏
//     */
//    public static <T> ObservableConverter backgroundToMain(LifecycleOwner lifecycleOwner, Observable<T> observable) {
//         return (ObservableConverter) observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).as(bindLifecycle(lifecycleOwner));
//    }

    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner) {
        if (null == lifecycleOwner)
            throw new NullPointerException("lifecycleOwner == null");
        return autoBind(lifecycleOwner);
    }

    private static <T> AutoDisposeConverter<T> autoBind(LifecycleOwner lifecycleOwner) {
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner)
        );
    }
}
