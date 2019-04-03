package com.dionisiofilho.sicoob.application.exceptions

import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.application.helpers.ResourcesHelper
import java.io.IOException

class NoInternetException : IOException() {

    override val message: String?
        get() = ResourcesHelper.getAppString(R.string.offline)
}