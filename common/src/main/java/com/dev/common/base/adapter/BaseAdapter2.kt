package com.lzt.common.base.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author guolong
 * @since 2019/6/5
 */
abstract class BaseAdapter2<T, K : RecyclerView.ViewHolder> : RecyclerView.Adapter<K>() {
    protected val mTag = javaClass.simpleName

    var data: List<T>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}