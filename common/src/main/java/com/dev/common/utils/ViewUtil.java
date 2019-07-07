package com.dev.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * @author guolong
 * @since 2019/4/24
 */
public class ViewUtil {
    public static View inflateView(@NonNull Context context, @NonNull ViewGroup parent, @LayoutRes int layout) {
        return LayoutInflater.from(context).inflate(layout, parent, false);
    }

    public static View inflateView(@NonNull ViewGroup parent, @LayoutRes int layout) {
        return inflateView(parent.getContext(), parent, layout);
    }

}
