package com.dev.component.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

/**
 *  start activity simple
 * @author guolong
 * @since 2019/6/20
 */
fun Context.startDetailActivity() {
    val intent = Intent().apply {
        action = "com.dev.DetailActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    startActivity(intent)
}

fun Activity.startDetailActivityWithOptions() {
    val intent = Intent().apply {
        action = "com.dev.DetailActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
}

fun Context.startSearchActivity() {
    val intent = Intent().apply {
        action = "com.dev.SearchActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    startActivity(intent)
}

fun Activity.startSearchActivityWithOptions(pair: Pair<View, String>? = null) {
    val intent = Intent().apply {
        action = "com.dev.SearchActivity"
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    if (pair == null) {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    } else {
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair).toBundle())
    }
}