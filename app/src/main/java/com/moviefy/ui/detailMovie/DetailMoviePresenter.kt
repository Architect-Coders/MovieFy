package com.moviefy.ui.detailMovie

import com.moviefy.data.database.Movie


class DetailMoviePresenter{

    private var view: View? = null

    interface View {
        fun updateUI(movie: Movie)
    }

    fun onCreate(view: View, movie: Movie) {
        this.view = view
        view.updateUI(movie)
    }

    fun onDestroy() {
        view = null
    }
}