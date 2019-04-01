package com.dionisiofilho.sicoob.extensions

import android.content.Context
import com.dionisiofilho.sicoob.application.helpers.ContextHelper


fun Any.getAppContext(): Context {
    return ContextHelper.getAppContext()
}