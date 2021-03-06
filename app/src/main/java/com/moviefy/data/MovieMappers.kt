package com.moviefy.data

import com.e.domain.Movie
import com.moviefy.data.database.Movie as DomainMovie
import com.moviefy.data.server.Movie as ServerMovie

fun Movie.toMovieUi(): DomainMovie =
    DomainMovie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favourite
    )

fun DomainMovie.toDomainMovie(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    voteAverage = voteAverage,
    favourite = favourite

)

fun ServerMovie.toServerToDomain(): Movie =
    Movie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        false
    )