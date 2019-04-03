package com.dionisiofilho.sicoob.application.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.dionisiofilho.sicoob.extensions.getAppContext

class NetworkHelper {

    companion object {
        fun isOnline(): Boolean {
            val cm = getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo: NetworkInfo? = cm.activeNetworkInfo

            return netInfo != null && netInfo.isConnected
        }
    }
}