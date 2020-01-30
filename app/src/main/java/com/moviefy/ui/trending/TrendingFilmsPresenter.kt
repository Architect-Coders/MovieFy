package com.moviefy.ui.trending

import com.e.usecases.AddFavouriteMovie
import com.moviefy.data.database.Movie
import com.e.usecases.FindTrendingMovies
import com.e.usecases.RemoveFavouriteMovie
import com.moviefy.data.toDomainMovie
import com.moviefy.data.toMovieUi
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class TrendingFilmsPresenter(private val findeTrendingMovies: FindTrendingMovies,  private val removeMovie: RemoveFavouriteMovie, private val addFavouriteMovie: AddFavouriteMovie) : Scope by Scope.Impl() {

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
            view.updateData(findeTrendingMovies().map { it.toMovieUi() })
            view.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie, isFavourite: Boolean, isOpenDetail: Boolean) {
        if(isOpenDetail) {
            view?.navigateTo(movie)
            return
        }

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
        this.view = null
        destroyScope()
    }
}