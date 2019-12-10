package com.moviefy.model.database

import com.moviefy.model.Movie

object FavouriteRepository: FavouriteMovieModel{

    private lateinit var database: FilmDatabase

    override fun getFavourites(): ArrayList<Movie>{
        return ArrayList(database.filmDao().loadAllFilms())
    }

    override fun addFavourite(film: Movie) {
        database.filmDao().insertFilm(film)
    }

    override fun removeFavourites(film: Movie) {
        database.filmDao().deleteFilm(film)
    }
}