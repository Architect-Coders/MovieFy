package com.moviefy.model

import android.app.Activity
import com.moviefy.R

class MoviesRepository(activity: Activity) {

    companion object{
        const val CERTIFICATION: String = "R"
        const val VOTEAVERAGE: String = "vote_average.desc"
    }

    private val apiKey = activity.getString(R.string.apy_key)
    private val regionRepository = RegionRepository(activity)
    private val dateRepository = DateRepository()

    suspend fun findReleaseFilms() =
        MovieDb.service
            .listReleaseFilmsAsync(
                apiKey,
                regionRepository.findLastRegion(),
                dateRepository.releaseFilmDate(),
                dateRepository.finalReleaseFilmDate(),
                dateRepository.releaseType()
            )
            .await()


    suspend fun findTrendingFilms() =
        MovieDb.service
            .listTrendingMoviesAsync(
                apiKey,
                regionRepository.findLastRegion(),
                CERTIFICATION,
                VOTEAVERAGE
            )
            .await()
}