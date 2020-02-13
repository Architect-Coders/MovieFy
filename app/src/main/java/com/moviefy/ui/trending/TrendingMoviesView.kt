package com.moviefy.ui.trending

import com.moviefy.data.database.Movie

interface TrendingMoviesView {

    fun showProgress()

    fun hideProgress()

    fun updateData(movies: List<Movie>)

    fun navigateTo(movie: Movie)

    fun saveInFavourites()

    fun removeFromFavourites()
}