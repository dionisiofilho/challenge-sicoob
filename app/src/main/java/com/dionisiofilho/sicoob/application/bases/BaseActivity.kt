package com.dionisiofilho.sicoob.application.bases

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.dionisiofilho.sicoob.application.helpers.AnimHelper
import com.dionisiofilho.sicoob.application.helpers.ToastHelper

open class BaseActivity : AppCompatActivity(), BaseView {


    override fun showOnError(error: String) {
        ToastHelper.showToastLong(error)
    }


    fun startActivityWithAnimation(intent: Intent) {
        startActivity(intent)
        AnimHelper.configureSlideEnterTransition(this)

    }

    fun finishWithSlideAnimation() {
        super.finish()
        AnimHelper.configureSlideExitTransition(this)
    }

}