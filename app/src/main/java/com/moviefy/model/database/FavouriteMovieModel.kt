package com.moviefy.model.database

import com.moviefy.model.Movie

interface FavouriteMovieModel{
    fun addFavourite(film: Movie)
    fun getFavourites(): ArrayList<Movie>
    fun removeFavourites(film: Movie)
}