package com.moviefy.model.database

import com.moviefy.MovieApplication
import com.moviefy.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepository(application: MovieApplication){

    private val database = application.database

    suspend fun getFavourites(): List<Movie> = withContext(Dispatchers.IO){
        with(database.filmDao()) {
            loadAllFilms()
        }
    }

    suspend fun addFavourite(film: Movie) = withContext(Dispatchers.IO){
        database.filmDao().insertFilm(film)
    }

    suspend fun removeFavourites(film: Movie) = withContext(Dispatchers.IO){
        database.filmDao().deleteFilm(film)
    }
}