package com.dionisiofilho.sicoob.application.helpers

import android.support.annotation.NonNull
import android.support.annotation.StringRes
import android.widget.Toast
import com.dionisiofilho.sicoob.extensions.getAppContext

class ToastHelper {

    companion object {

        fun showToastLong(message: String) {
            Toast.makeText(getAppContext(), message, Toast.LENGTH_LONG).show()
        }

        fun showToastLong(@StringRes @NonNull message: Int) {
            Toast.makeText(getAppContext(), message, Toast.LENGTH_LONG).show()
        }

        fun showToastShort(message: String) {
            Toast.makeText(getAppContext(), message, Toast.LENGTH_SHORT).show()
        }

        fun showToastShort(@StringRes @NonNull message: Int) {
            Toast.makeText(getAppContext(), message, Toast.LENGTH_SHORT).show()
        }

    }
}