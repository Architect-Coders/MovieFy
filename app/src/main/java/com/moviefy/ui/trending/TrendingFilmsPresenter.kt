package com.moviefy.ui.trending

import com.moviefy.data.database.Movie
import com.e.usecases.FindTrendingMovies
import com.moviefy.data.toRoomMovie
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class TrendingFilmsPresenter(private val findeTrendingMovies: FindTrendingMovies) : Scope by Scope.Impl() {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(movies: List<Movie>)
        fun navigateTo(movie: Movie)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        initScope()
        this.view = view

        launch {
            view.showProgress()
            view.updateData(findeTrendingMovies().map { it.toRoomMovie() })
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