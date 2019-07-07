package com.dev.main.activities

import androidx.lifecycle.ViewModel
import com.dev.component.base.BaseCompatActivity
import com.dev.main.R

class MainActivity : BaseCompatActivity<EmptyViewModel>() {
    override val contentLayoutId: Int
        get() = R.layout.activity_main
}

class EmptyViewModel : ViewModel()
