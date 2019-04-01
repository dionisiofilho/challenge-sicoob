package com.dionisiofilho.sicoob.application.helpers

import android.app.Activity
import com.dionisiofilho.sicoob.R
import org.jetbrains.annotations.NotNull

class AnimHelper {

    companion object {

        fun configureSlideEnterTransition(@NotNull activity: Activity) {
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        fun configureSlideExitTransition(@NotNull activity: Activity) {
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

    }
}