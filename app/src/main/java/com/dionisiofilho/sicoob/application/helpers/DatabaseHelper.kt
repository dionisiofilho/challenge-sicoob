package com.dionisiofilho.sicoob.application.helpers

import android.arch.persistence.room.Room
import com.dionisiofilho.sicoob.application.managers.AppDatabase
import com.dionisiofilho.sicoob.extensions.getAppContext

class DatabaseHelper {

    companion object {

        private const val databaseName = "challenge-siccob"

        fun getInstanceDatabase(): AppDatabase {
            return Room.databaseBuilder(getAppContext(), AppDatabase::class.java, databaseName).allowMainThreadQueries().build()
        }

    }
}