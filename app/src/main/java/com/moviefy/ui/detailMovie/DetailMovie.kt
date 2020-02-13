package com.moviefy.ui.detailMovie

import android.os.Bundle
import com.moviefy.R
import com.moviefy.data.database.Movie
import com.moviefy.ui.base.GenericToolbarActivity
import com.moviefy.ui.common.loadImage
import kotlinx.android.synthetic.main.movie_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class DetailMovie: GenericToolbarActivity(), DetailMovieView {

    companion object {
        const val MOVIE = "DetailMovie:movie"
    }

    private val presenter: DetailMoviePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        presenter.onCreate(intent.getParcelableExtra(MOVIE))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateUI(movie: Movie) {
        configureActionBar(movie.title)

        movie.posterPath?.let {
        posterFilm.loadImage(movie.posterPath)
        }
        moviePuntuation.text = movie.voteAverage.toString()
        movieDescription.text = movie.overview

    }
}