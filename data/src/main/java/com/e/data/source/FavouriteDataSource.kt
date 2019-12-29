package com.e.data.source

import com.e.domain.Movie

interface FavouriteDataSource {

    suspend fun getFavourites(): List<Movie>

    suspend fun addFavourite(film: Movie)

    suspend fun removeFavourites(film: Movie)
}