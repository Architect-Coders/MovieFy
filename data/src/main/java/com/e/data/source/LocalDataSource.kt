package com.e.data.source

import com.e.domain.Movie

interface LocalDataSource {

    suspend fun isEmpty(): Boolean

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getReleasesMovies(): List<Movie>
}