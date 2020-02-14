package com.moviefy.ui.detailMovie

import com.moviefy.data.database.Movie

interface DetailMovieView {
    fun updateUI(movie: Movie)
}