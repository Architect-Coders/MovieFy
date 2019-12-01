package com.moviefy.ui.detailMovie

import android.os.Bundle
import com.moviefy.R
import com.moviefy.model.Movie
import com.moviefy.ui.base.GenericToolbarActivity
import com.moviefy.ui.common.loadImage
import kotlinx.android.synthetic.main.movie_detail.*


class DetailMovie: GenericToolbarActivity(), DetailMoviePresenter.View {

    companion object {
        const val MOVIE = "DetailMovie:movie"
    }

    private val presenter = DetailMoviePresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        presenter.onCreate(this, intent.getParcelableExtra(MOVIE))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateUI(movie: Movie) {
        configureActionBar(movie.title)

        posterFilm.loadImage(movie.posterPath)
        moviePuntuation.text = movie.voteAverage.toString()
        movieDescription.text = movie.overview

    }
}