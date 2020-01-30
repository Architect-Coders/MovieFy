package com.moviefy.data.database

import android.content.Context
import androidx.room.*

@Database(entities = [Movie::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            "movie-db"
        ).build()
    }

    abstract fun movieDao(): FilmDao
}