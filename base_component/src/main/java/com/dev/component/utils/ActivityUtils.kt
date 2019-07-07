package com.lzt.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

/**
 *
 * @author guolong
 * @since 2019/6/20
 */
fun Context.startDetailActivity(zi: String, saveHistory: Boolean = false) {
    val intent = Intent().apply {
        action = "com.lzt.zidian.DetailActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    startActivity(intent)
}

fun Activity.startDetailActivityWithOptions(zi: String, saveHistory: Boolean = false) {
    val intent = Intent().apply {
        action = "com.lzt.zidian.DetailActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
}

fun Context.startSearchActivity() {
    val intent = Intent().apply {
        action = "com.lzt.zidian.SearchActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    startActivity(intent)
}

fun Activity.startSearchActivityWithOptions(pair: Pair<View, String>? = null) {
    val intent = Intent().apply {
        action = "com.lzt.zidian.SearchActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    if (pair == null) {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    } else {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair).toBundle())
    }
}