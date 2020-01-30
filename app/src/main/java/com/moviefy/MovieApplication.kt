package com.moviefy

import android.app.Application
import com.moviefy.di.initDI

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}