package com.moviefy.data.server

import com.e.data.source.RemoteDataSource
import com.moviefy.data.toDomainMovie

class MovieDataSource: RemoteDataSource {

    override suspend fun listReleaseFilms(apiKey: String, region: String, releaseFilmDate: String, finalReleaseFilmDate: String, releaseType: String)
            : List<com.e.domain.Movie> =
        MovieDb.service
            .listReleaseFilmsAsync(
                apiKey = apiKey,
                region = region,
                releaseDate = releaseFilmDate,
                withReleaseDate = finalReleaseFilmDate,
                withReleaseType = releaseType).await()
            .results
            .map { it.toDomainMovie() }

}