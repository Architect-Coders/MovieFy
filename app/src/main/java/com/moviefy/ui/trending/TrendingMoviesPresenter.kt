package com.moviefy.ui.trending

import com.e.usecases.AddFavouriteMovie
import com.moviefy.data.database.Movie
import com.e.usecases.FindTrendingMovies
import com.e.usecases.RemoveFavouriteMovie
import com.moviefy.data.toDomainMovie
import com.moviefy.data.toMovieUi
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class TrendingMoviesPresenter(private var view: TrendingMoviesView? = null, private val findeTrendingMovies: FindTrendingMovies, private val removeMovie: RemoveFavouriteMovie, private val addFavouriteMovie: AddFavouriteMovie) : Scope by Scope.Impl() {

    fun onCreate() {
        initScope()

        launch {
            view?.showProgress()
            view?.updateData(findeTrendingMovies().map { it.toMovieUi() })
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