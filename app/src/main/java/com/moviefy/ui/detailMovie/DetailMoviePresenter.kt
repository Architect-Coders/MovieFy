package com.moviefy.ui.detailMovie

import com.moviefy.data.database.Movie


class DetailMoviePresenter(private var view: DetailMovieView? = null){

    fun onCreate(movie: Movie) {
        view?.updateUI(movie)
    }

    fun onDestroy() {
        view = null
    }
}