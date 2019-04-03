package com.dionisiofilho.sicoob.extensions

import android.support.v4.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.show() {
    if (!this.isRefreshing) {
        this.isRefreshing = true
    }
}

fun SwipeRefreshLayout.dimiss() {
    if (this.isRefreshing) {
        this.isRefreshing = false
    }
}