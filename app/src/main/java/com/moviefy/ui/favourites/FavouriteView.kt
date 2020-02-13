package com.moviefy.ui.favourites

import com.moviefy.data.database.Movie

interface FavouriteView {

    fun showProgress()

    fun hideProgress()

    fun updateData(movies: List<Movie>)

    fun navigateTo(movie: Movie)

    fun emptyFavourites()

    fun saveInFavourites()

    fun removeFromFavourites()

}
