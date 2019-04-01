package com.dionisiofilho.sicoob.application.bases

import android.content.Context

open class BasePresenter<P : BaseView>(private val ctx: Context, private val view: P) {

    fun getPresenterContext(): Context {
        return ctx
    }

    fun getView(): P {
        return view
    }

}