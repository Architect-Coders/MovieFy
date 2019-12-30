package com.e.data.source

import com.e.domain.Movie


interface RemoteDataSource {

    suspend fun listReleaseFilms(apiKey: String, region: String, releaseFilmDate: String, finalReleaseFilmDate: String, releaseType: String): List<Movie>

    suspend fun findTrendingMovies(apiKey: String, region: String, certification: String, voteAverage: String): List<Movie>

}