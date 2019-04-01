package com.dionisiofilho.sicoob.application.bases

import android.content.Intent
import android.support.v4.app.Fragment
import com.dionisiofilho.sicoob.application.helpers.AnimHelper
import com.dionisiofilho.sicoob.application.helpers.ToastHelper

open class BaseFragment : Fragment(), BaseView {

    override fun showOnError(error: String) {
        ToastHelper.showToastLong(error)
    }

    fun startActivityWithAnimation(intent: Intent) {
        startActivity(intent)
        AnimHelper.configureSlideEnterTransition(requireActivity())

    }

    fun finishWithSlideAnimation() {
        requireActivity().finish()
        AnimHelper.configureSlideExitTransition(requireActivity())
    }

}