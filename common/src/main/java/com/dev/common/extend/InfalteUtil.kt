package com.dev.common.extend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author guolong
 * @since 2019/4/27
 */
val RecyclerView.ViewHolder.inflater: LayoutInflater
    get() = android.view.LayoutInflater.from(itemView.context)

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

val RecyclerView.ViewHolder.context: Context
    get() = itemView.context

fun ViewGroup.inflater(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}