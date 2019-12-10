package com.moviefy.model.database

import androidx.room.*
import com.moviefy.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}


@Dao
interface FilmDao {
    @Query("SELECT * FROM movie ORDER BY uid")
    fun loadAllFilms(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Movie)

    @Delete
    fun deleteFilm(film: Movie)
}