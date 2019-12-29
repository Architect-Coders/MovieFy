package com.moviefy.model.database

import com.moviefy.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FavouriteMovieModel{
    suspend fun addFavourite(film: Movie)
    suspend fun getFavourites(): List<Movie>
    suspend fun removeFavourites(film: Movie)

}