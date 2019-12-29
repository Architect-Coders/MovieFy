package com.moviefy

import android.app.Application
import androidx.room.Room
import com.moviefy.data.database.FilmDatabase

class MovieApplication : Application() {

    lateinit var database: FilmDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, FilmDatabase::class.java, "list-master-db").build()
    }
}