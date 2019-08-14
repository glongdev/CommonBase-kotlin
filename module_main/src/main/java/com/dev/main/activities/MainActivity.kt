package com.dev.main.activities

import androidx.lifecycle.ViewModel
import com.dev.common.base.BaseCompatActivity
import com.dev.main.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseCompatActivity<EmptyViewModel>() {
    override val contentLayoutId: Int
        get() = R.layout.activity_main

    override fun setupViews() {
        setupToolbar(toolbar)
    }
}

class EmptyViewModel : ViewModel()
