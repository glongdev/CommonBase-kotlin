package com.lzt.common.base.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * @author guolong
 * @since 2019/4/24
 */
abstract class BaseAdapter<T, K : RecyclerView.ViewHolder> : RecyclerView.Adapter<K>() {
    protected val mTag = javaClass.simpleName

    var data: MutableList<T>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addDataList(list: List<T>) {
        data!!.addAll(list)
        notifyItemRangeInserted(data!!.size - list.size, list.size)
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }
}
