package com.e.data.repository

import com.e.data.source.RemoteDataSource
import com.e.domain.Movie


class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val dateRepository: DateRepository,
    private val regionRepository: RegionRepository,
    private val apikey: String){

    companion object{
        const val CERTIFICATION: String = "R"
        const val VOTEAVERAGE: String = "vote_average.desc"
    }

    suspend fun getReleaseFilms(): List<Movie> {
        return remoteDataSource.getReleaseFilms(
            apiKey = apikey,
            region = regionRepository.findLastRegion(),
            releaseFilmDate = dateRepository.releaseFilmDate(),
            finalReleaseFilmDate = dateRepository.finalReleaseFilmDate(),
            releaseType = dateRepository.releaseType())
    }

    suspend fun findTrendingFilms(): List<Movie>{
        return remoteDataSource.findTrendingMovies(
            apiKey = apikey,
            region = regionRepository.findLastRegion(),
            certification = CERTIFICATION,
            voteAverage = VOTEAVERAGE
        )
    }
}