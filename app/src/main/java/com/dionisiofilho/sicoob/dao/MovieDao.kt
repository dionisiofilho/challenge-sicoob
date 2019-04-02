package com.dionisiofilho.sicoob.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dionisiofilho.sicoob.model.Movie
import org.jetbrains.annotations.NotNull

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAllMovieDatabase(): List<Movie>

    @Insert
    fun inserMovieDatabase(@NotNull movie: Movie)

    @Delete
    fun deleteMovieFavorite(@NotNull movie: Movie)

    @Query("SELECT * FROM Movie WHERE id IN (:idMovie)")
    fun verifyMovieIsFavorit(@NotNull idMovie: Int): Boolean

}