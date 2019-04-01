package com.dionisiofilho.sicoob.application

import android.support.multidex.MultiDexApplication
import com.dionisiofilho.sicoob.application.helpers.ContextHelper

class SicoobApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()

        ContextHelper.setAppContext(applicationContext)

    }


}