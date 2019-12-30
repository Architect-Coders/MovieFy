package com.e.usecases

import com.e.data.repository.FavouriteRepository
import com.e.domain.Movie

class GetFavouritesMovies(private val favouriteRepository: FavouriteRepository) {
    suspend operator fun invoke(): List<Movie>{
        return favouriteRepository.getFavouritesMovies()
    }
}