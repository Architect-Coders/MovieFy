package com.moviefy.ui.releaseFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import com.moviefy.R
import com.moviefy.data.database.Movie
import com.moviefy.ui.common.showToast
import com.moviefy.ui.navigator.Navigator
import com.moviefy.ui.releaseFilms.adapter.MoviesAdapter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class ReleaseFilmsFragment : Fragment(), ReleaseFilmsView {

    private val presenter: ReleaseFilmsPresenter by inject { parametersOf(this) }
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, parent, false)

        presenter.let { presenter ->
            presenter.onCreate()
            adapter =
                MoviesAdapter { movie, isSave, openDetail ->
                    presenter.onMovieClicked(movie, openDetail, isSave)
                }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateData(movies: List<Movie>) {
        adapter?.movies = movies
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun navigateTo(movie: Movie) {
        activity?.let {activity ->
            Navigator.MovieDetail.openDetail(activity, movie)
        }
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun saveInFavourites() {
        activity?.showToast(getString(R.string.movie_save_favourite))
    }

    override fun removeFromFavourites() {
        activity?.showToast(getString(R.string.movie_remove_favourite))
    }
}