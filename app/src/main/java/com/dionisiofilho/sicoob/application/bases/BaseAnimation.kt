package com.dionisiofilho.sicoob.application.bases

import android.view.animation.Animation

abstract class BaseAnimation : Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation) {
        animateEnd(animation)
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    abstract fun animateEnd(animation: Animation)
}