package com.moviefy.ui.releaseFilms

import com.moviefy.model.Movie
import com.moviefy.model.MoviesRepository
import com.moviefy.model.database.FavouriteRepository
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class ReleaseFilmsPresenter(private val moviesRepository: MoviesRepository, private val favouriteRepository: FavouriteRepository) : Scope by Scope.Impl() {

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
            view.updateData(moviesRepository.findReleaseFilms().results)
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
                favouriteRepository.addFavourite(movie)
                view?.saveInFavourites()
            } else {
                favouriteRepository.removeFavourites(movie)
                view?.removeFromFavourites()
            }
        }
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}