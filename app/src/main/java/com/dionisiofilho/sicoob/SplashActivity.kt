package com.dionisiofilho.sicoob

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.dionisiofilho.sicoob.application.bases.BaseActivity
import com.dionisiofilho.sicoob.main.MainActivity


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        configureSplash()


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        Handler().apply {
            postDelayed({
                init()
            }, 2000)
        }
    }

    private fun configureSplash() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.blink)
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE
        animation.duration = 700

        val imageSplash = findViewById<ImageView>(R.id.iv_splash)
        imageSplash.startAnimation(animation)


    }

    private fun init() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityWithAnimation(intent)
        finishWithSlideAnimation()
    }
}
