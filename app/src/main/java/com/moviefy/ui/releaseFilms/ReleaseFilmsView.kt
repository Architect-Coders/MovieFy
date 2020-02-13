package com.moviefy.ui.releaseFilms

import com.moviefy.data.database.Movie

interface ReleaseFilmsView {

    fun showProgress()

    fun hideProgress()

    fun updateData(movies: List<Movie>)

    fun navigateTo(movie: Movie)

    fun saveInFavourites()

    fun removeFromFavourites()

}