package com.moviefy.data.server

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("discover/movie?")
    fun listReleaseFilmsAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("primary_release_date.gte=") releaseDate: String,
        @Query("primary_release_date.lte=") withReleaseDate: String,
        @Query("with_release_type=") withReleaseType: String
        ): Deferred<MovieDbResult>

    @GET("discover/movie?")
    fun findTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("countryId") region: String,
        @Query("certification") certification: String,
        @Query("sort_by") sortBy: String

    ): Deferred<MovieDbResult>
}


