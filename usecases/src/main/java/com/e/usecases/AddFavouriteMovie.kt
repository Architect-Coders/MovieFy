package com.e.usecases

import com.e.data.repository.MoviesRepository
import com.e.domain.Movie

class AddFavouriteMovie(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(movie: Movie){
        moviesRepository.addFavouriteMovie(movie)
    }
}