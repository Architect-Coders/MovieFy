package com.moviefy.ui.favourites

import com.e.usecases.AddFavouriteMovie
import com.e.usecases.GetFavouritesMovies
import com.e.usecases.RemoveFavouriteMovie
import com.moviefy.data.database.Movie
import com.moviefy.data.database.RoomDataSource
import com.moviefy.data.toDomainMovie
import com.moviefy.data.toMovieUi
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class FavouritePresenter(private var view: FavouriteView? = null, private val getFavouritesMovies: GetFavouritesMovies, private val removeMovie: RemoveFavouriteMovie, private val addFavouriteMovie: AddFavouriteMovie): Scope by Scope.Impl()  {


    fun onCreate() {
        initScope()

        launch {
            view?.showProgress()

            if(getFavouritesMovies().isNotEmpty()){
                view?.updateData(getFavouritesMovies().map { it.toMovieUi() })
            }else{
                view?.emptyFavourites()
            }

            view?.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie, isSave: Boolean, isOpenDetail: Boolean) {
        if(isOpenDetail) {
            view?.navigateTo(movie)
            return
        }

        launch {
            if (isSave) {
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