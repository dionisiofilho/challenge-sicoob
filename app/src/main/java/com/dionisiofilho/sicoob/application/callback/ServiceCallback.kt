package com.dionisiofilho.sicoob.application.callback

import com.dionisiofilho.sicoob.R
import com.dionisiofilho.sicoob.application.bases.BasePresenterCallback
import com.dionisiofilho.sicoob.application.helpers.ResourcesHelper
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceCallback<C> {

    fun build(basePresenterCallback: BasePresenterCallback<C>): Callback<C> {

        return object : Callback<C> {

            override fun onResponse(call: Call<C>, response: Response<C>) {

                try {
                    if (response.isSuccessful) {

                        response.body()?.let { basePresenterCallback.onSuccess(it) }

                    } else {

                        if (response.errorBody() != null) {

                            response.errorBody()?.let { error ->
                                val jObjError = JSONObject(error.string())

                                val throwable = if (!jObjError.isNull("status_message")) {
                                    Throwable(jObjError.get("status_message").toString())
                                } else {
                                    Throwable(Throwable(ResourcesHelper.getAppString(R.string.error_service)))
                                }

                                onFailure(call, throwable)
                            } ?: run {
                                onFailure(call, Throwable(ResourcesHelper.getAppString(R.string.error_service)))
                            }


                        }

                    }
                } catch (e: Exception) {
                    onFailure(call, Throwable(e.message))
                }

            }

            override fun onFailure(call: Call<C>, t: Throwable) {
                t.message?.let {
                    basePresenterCallback.onError(t)
                } ?: run {
                    basePresenterCallback.onError(Throwable(ResourcesHelper.getAppString(R.string.error_service)))
                }
            }


        }
    }
}