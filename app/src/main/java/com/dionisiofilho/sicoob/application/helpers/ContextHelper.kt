package com.dionisiofilho.sicoob.application.helpers

import android.content.Context
import org.jetbrains.annotations.NotNull

class ContextHelper {

    companion object {

        lateinit var contextApplication: Context

        fun getAppContext(): Context {
            return contextApplication
        }

        fun setAppContext(@NotNull context: Context) {
            this.contextApplication = context
        }
    }
}