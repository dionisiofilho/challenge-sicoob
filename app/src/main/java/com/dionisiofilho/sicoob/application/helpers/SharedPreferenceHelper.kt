package com.dionisiofilho.sicoob.application.helpers

import android.content.Context
import android.content.SharedPreferences
import com.dionisiofilho.sicoob.extensions.getAppContext
import org.jetbrains.annotations.NotNull

object SharedPreferenceHelper {

    private const val PREF_NAME = "challenge-sicoob"
    private const val LANGUAGE = "language"

    private val sharedPreference: SharedPreferences = getAppContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun setLanguage(@NotNull language: String) {
        sharedPreference.edit().putString(LANGUAGE, language).apply()
    }

    fun getLanguage(): String {
        sharedPreference.getString(LANGUAGE, "pt-BR")?.let {
            return it
        } ?: run {
            return "pt-BR"
        }
    }


}