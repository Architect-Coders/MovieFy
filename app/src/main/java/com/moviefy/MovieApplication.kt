package com.moviefy

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.moviefy.model.database.FilmDatabase

class MovieApplication : Application() {

    lateinit var database: FilmDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, FilmDatabase::class.java, "list-master-db").build()
    }
}