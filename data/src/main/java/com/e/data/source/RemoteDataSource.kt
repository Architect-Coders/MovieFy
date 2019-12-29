package com.e.data.source

import com.e.domain.Movie


interface RemoteDataSource {

    suspend fun listReleaseFilms(apiKey: String, region: String, releaseFilmDate: String, finalReleaseFilmDate: String, releaseType: String): List<Movie>
}