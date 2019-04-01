package com.dionisiofilho.sicoob

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.dionisiofilho.sicoob.application.bases.BaseActivity
import com.dionisiofilho.sicoob.main.MainActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().apply {
            postDelayed({
                init()
            }, 2000)
        }

    }

    private fun init() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityWithAnimation(intent)
        finishWithSlideAnimation()
    }
}
