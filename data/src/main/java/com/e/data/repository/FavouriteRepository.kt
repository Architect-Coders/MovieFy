package com.e.data.repository

import com.e.data.source.FavouriteDataSource
import com.e.domain.Movie

class FavouriteRepository(private val favouriteDataSource: FavouriteDataSource) {

    suspend fun getFavouritesMovies(): List<Movie> {
        return favouriteDataSource.getFavourites()
    }
}