package com.dev.common.base

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import java.util.ArrayList

/**
 * @author GarrettLance
 * @since 2019/4/7
 */
class BaseFragmentPageAdapter<T : BaseFragment<*>>(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    protected var fragments: MutableList<T> = ArrayList()

    fun addFragment(fragment: T) {
        fragments.add(fragment)
    }

    override fun getItem(position: Int): T {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}
