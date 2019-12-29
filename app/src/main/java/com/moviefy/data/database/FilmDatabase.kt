package com.moviefy.data.database

import androidx.room.*

@Database(entities = [Movie::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}


@Dao
interface FilmDao {
    @Query("SELECT * FROM movie ORDER BY id")
    fun loadAllFilms(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Movie)

    @Delete
    fun deleteFilm(film: Movie)
}