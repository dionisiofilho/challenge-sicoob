package com.dionisiofilho.sicoob.application.widgets

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.extensions.getAppContext

class Loading(ctx: Context) : View(ctx) {

    private var dialog: Dialog = Dialog(context)
    private var view: View = inflate(context, R.layout.loading, null)

    init {

        dialog.setCancelable(false)
        dialog.setContentView(view)
        val animation = AnimationUtils.loadAnimation(getAppContext(), R.anim.zoom)

        WindowManager.LayoutParams().apply {

            dialog.window?.let {
                copyFrom(it.attributes)
                width = WindowManager.LayoutParams.WRAP_CONTENT
                height = WindowManager.LayoutParams.WRAP_CONTENT

                it.attributes = this
            }

        }

        view.startAnimation(animation)
    }


    fun show() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun hide() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}