package com.moviefy.ui.releaseFilms

import com.e.usecases.AddFavouriteMovie
import com.e.usecases.GetReleasesMovies
import com.e.usecases.RemoveFavouriteMovie
import com.moviefy.data.database.RoomDataSource
import com.moviefy.data.database.Movie
import com.moviefy.data.toDomainMovie
import com.moviefy.data.toMovieUi
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class ReleaseFilmsPresenter(private val getReleasesMovies: GetReleasesMovies, private val removeMovie: RemoveFavouriteMovie, private val addFavouriteMovie: AddFavouriteMovie) : Scope by Scope.Impl() {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(movies: List<Movie>)
        fun navigateTo(movie: Movie)
        fun saveInFavourites()
        fun removeFromFavourites()
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

        launch {
            view.showProgress()
            view.updateData(getReleasesMovies().map { it.toMovieUi() })
            view.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie, favourite: Boolean, openDetail: Boolean) {
        if(openDetail) {
            view?.navigateTo(movie)
            return
        }

        launch {
            if (favourite) {
                movie.favourite = true
                addFavouriteMovie(movie.toDomainMovie())
                view?.saveInFavourites()
            } else {
                movie.favourite = false
                removeMovie(movie.toDomainMovie())
                view?.removeFromFavourites()
            }
        }
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}