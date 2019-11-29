package com.moviefy.model

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("discover/movie?")
    fun listReleaseFilmssAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("release_date.gte=") releaseDate: String,
        @Query("release_date.lte=") withReleaseDate: String,
        @Query("with_release_type=") withReleaseType: String
        ): Deferred<MovieDbResult>
}