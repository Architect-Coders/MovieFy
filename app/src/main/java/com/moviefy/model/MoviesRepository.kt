package com.moviefy.model

import android.app.Activity
import com.moviefy.R

class MoviesRepository(activity: Activity) {

    private val apiKey = activity.getString(R.string.apy_key)
    private val regionRepository = RegionRepository(activity)
    private val dateRepository = DateRepository()

    suspend fun findReleaseFilms() =
        MovieDb.service
            .listReleaseFilmssAsync(
                apiKey,
                regionRepository.findLastRegion(),
                dateRepository.releaseFilmDate(),
                dateRepository.finalReleaseFilmDate(),
                dateRepository.releaseType()
            )
            .await()
}