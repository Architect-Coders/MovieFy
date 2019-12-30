package com.moviefy.data.server

import com.e.data.source.RemoteDataSource
import com.e.domain.Movie
import com.moviefy.data.toDomainMovie

class MovieDataSource: RemoteDataSource {

    override suspend fun listReleaseFilms(apiKey: String, region: String, releaseFilmDate: String, finalReleaseFilmDate: String, releaseType: String)
            : List<Movie> =
        MovieDb.service
            .listReleaseFilmsAsync(
                apiKey = apiKey,
                region = region,
                releaseDate = releaseFilmDate,
                withReleaseDate = finalReleaseFilmDate,
                withReleaseType = releaseType).await()
            .results
            .map { it.toDomainMovie() }

    override suspend fun findTrendingMovies(apiKey: String, region: String, certification: String, voteAverage: String): List<Movie> =
        MovieDb.service
            .findTrendingMoviesAsync(
                apiKey = apiKey,
                region = region,
                certification = certification,
                sortBy = voteAverage
            ).await()
            .results
            .map { it.toDomainMovie() }
}