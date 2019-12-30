package com.moviefy.ui.favourites

import com.e.usecases.GetFavouritesMovies
import com.moviefy.data.database.Movie
import com.moviefy.data.toMovieUi
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class FavouritePresenter(private val getFavouritesMovies: GetFavouritesMovies): Scope by Scope.Impl()  {


    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(movies: List<Movie>)
        fun navigateTo(movie: Movie)
        fun emptyFavourites()
    }

    private var view: View? = null

    fun onCreate(view: FavouritePresenter.View) {
        initScope()
        this.view = view

        launch {
            view.showProgress()

            if(getFavouritesMovies().isNotEmpty()){
                view.updateData(getFavouritesMovies().map { it.toMovieUi() })
            }else{
                view.emptyFavourites()
            }

            view.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie, isSave: Boolean, isOpenDetail: Boolean) {
        view?.navigateTo(movie)
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}