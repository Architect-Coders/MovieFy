package com.moviefy.data.server

import com.e.data.source.RemoteDataSource
import com.e.domain.Movie
import com.moviefy.data.toServerToDomain

class MovieDataSource(private val modibeDb: MovieDb): RemoteDataSource {

    override suspend fun getReleaseFilms(apiKey: String, region: String, releaseFilmDate: String, finalReleaseFilmDate: String, releaseType: String)
            : List<Movie> =
        modibeDb.service
            .listReleaseFilmsAsync(
                apiKey = apiKey,
                region = region,
                releaseDate = releaseFilmDate,
                withReleaseDate = finalReleaseFilmDate,
                withReleaseType = releaseType).await()
            .results
            .map { it.toServerToDomain() }

    override suspend fun findTrendingMovies(apiKey: String, region: String, certification: String, voteAverage: String): List<Movie> =
        modibeDb.service
            .findTrendingMoviesAsync(
                apiKey = apiKey,
                region = region,
                certification = certification,
                sortBy = voteAverage
            ).await()
            .results
            .map { it.toServerToDomain() }
}