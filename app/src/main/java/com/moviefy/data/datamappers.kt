package com.moviefy.data

import com.e.domain.Movie
import com.moviefy.data.database.Movie as DomainMovie
import com.moviefy.data.server.Movie as ServerMovie

fun Movie.toMovieUi(): DomainMovie =
    DomainMovie(
        uid,
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage
    )

fun DomainMovie.toDomainMovie(): Movie = Movie(
    uid = uid,
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    voteAverage = voteAverage
)

fun ServerMovie.toServerToDomain(): Movie =
    Movie(
        0,
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage
    )