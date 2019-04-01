package com.dionisiofilho.sicoob.application.helpers

import android.graphics.drawable.Drawable
import com.dionisiofilho.sicoob.extensions.getAppContext
import org.jetbrains.annotations.NotNull

class ResourcesHelper {

    companion object {

        fun getAppString(@NotNull idRes: Int, vararg formatArg: Any): String {
            return getAppContext().getString(idRes, formatArg)
        }

        fun getDrawable(@NotNull idRes: Int): Drawable? {
            return getAppContext().getDrawable(idRes)
        }


    }
}