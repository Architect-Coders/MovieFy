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

class ReleaseFilmsPresenter(private var view: ReleaseFilmsView? = null, private val getReleasesMovies: GetReleasesMovies, private val removeMovie: RemoveFavouriteMovie, private val addFavouriteMovie: AddFavouriteMovie) : Scope by Scope.Impl() {

    fun onCreate() {
        initScope()

        launch {
            view?.showProgress()
            view?.updateData(getReleasesMovies().map { it.toMovieUi() })
            view?.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie, isOpenDetail: Boolean) {
        if(isOpenDetail) {
            view?.navigateTo(movie)
        }
    }

    fun updateFavourites(movie: Movie, isFavourite: Boolean){
        launch {
            if (isFavourite) {
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
        view = null
        destroyScope()
    }
}