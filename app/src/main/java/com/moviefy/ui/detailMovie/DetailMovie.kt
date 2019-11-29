package com.moviefy.ui.detailMovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moviefy.R


class DetailMovie: AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailMovie:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

    }

}