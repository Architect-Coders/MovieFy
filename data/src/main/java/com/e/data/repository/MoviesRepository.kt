package com.e.data.repository

import com.e.data.source.RemoteDataSource
import com.e.domain.Movie


class MoviesRepository(
    private val apikey: String,
    private val remoteDataSource: RemoteDataSource,
    private val dateRepository: DateRepository,
    private val regionRepository: RegionRepository){

    companion object{
        const val CERTIFICATION: String = "R"
        const val VOTEAVERAGE: String = "vote_average.desc"
    }

    suspend fun getReleaseFilms(): List<Movie> {

        return remoteDataSource.listReleaseFilms(
            apikey,
            regionRepository.findLastRegion(),
            dateRepository.releaseFilmDate(),
            dateRepository.finalReleaseFilmDate(),
            dateRepository.releaseType())
    }


//    suspend fun findTrendingFilms() =
//        MovieDb.service
//            .listTrendingMoviesAsync(
//                apiKey,
//                regionRepository.findLastRegion(),
//                CERTIFICATION,
//                VOTEAVERAGE
//            )
//            .await()
}