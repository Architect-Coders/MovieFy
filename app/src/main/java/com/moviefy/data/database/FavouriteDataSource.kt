package com.moviefy.data.database

import com.e.data.source.FavouriteDataSource
import com.e.domain.Movie
import com.moviefy.data.toDomainMovie
import com.moviefy.data.toMovieUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteDataSource(data: FilmDatabase): FavouriteDataSource {

    private val database = data.filmDao()
    override suspend fun getFavourites(): List<Movie> = withContext(Dispatchers.IO) {
        database.loadAllFilms().map { it.toDomainMovie() }
    }

    override suspend fun addFavourite(film: Movie) = withContext(Dispatchers.IO) {
        database.insertFilm(film.toMovieUi())
    }

    override suspend fun removeFavourites(film: Movie) = withContext(Dispatchers.IO) {
        database.deleteFilm(film.toMovieUi())
    }
}