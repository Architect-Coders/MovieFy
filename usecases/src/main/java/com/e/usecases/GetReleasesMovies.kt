package com.e.usecases

import com.e.data.repository.MoviesRepository
import com.e.domain.Movie

class GetReleasesMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(): List<Movie>{
        return moviesRepository.getReleaseFilms()
    }
}