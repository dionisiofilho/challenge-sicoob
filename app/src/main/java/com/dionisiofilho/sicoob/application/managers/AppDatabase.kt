package com.dionisiofilho.sicoob.application.managers

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dionisiofilho.sicoob.dao.MovieDao
import com.dionisiofilho.sicoob.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
