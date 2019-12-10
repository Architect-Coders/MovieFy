package com.moviefy.ui.trending

import com.moviefy.model.Movie
import com.moviefy.model.MoviesRepository
import com.moviefy.ui.common.Scope
import kotlinx.coroutines.launch

class TrendingFilmsPresenter(private val moviesRepository: MoviesRepository) : Scope by Scope.Impl() {

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
            view.updateData(moviesRepository.findTrendingFilms().results)
            view.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie) {
        view?.navigateTo(movie)
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}