package com.dionisiofilho.sicoob.application.bases

import com.dionisiofilho.sicoob.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class BaseConnectionService {

    companion object {

        fun <T> instance(kllass: Class<T>): T {
            return BaseConnectionService.retrofit().create(kllass)
        }

        private fun retrofit(): Retrofit {

            val client = OkHttpClient.Builder()
            client.readTimeout(30, TimeUnit.SECONDS)
            client.connectTimeout(15, TimeUnit.SECONDS)


            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(interceptor)
            }

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.BaseURLAPI)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(client.build())
                    .build()
        }

    }
}