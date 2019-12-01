package com.moviefy.ui.releaseFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moviefy.model.Movie
import com.moviefy.model.MoviesRepository
import kotlinx.android.synthetic.main.fragment_home.*
import com.moviefy.R
import com.moviefy.ui.navigator.Navigator


class ReleaseFilmsFragment : Fragment(), ReleaseFilmsPresenter.View {

    private val presenter by lazy { ReleaseFilmsPresenter(MoviesRepository(activity!!)) }
    private val adapter = MoviesAdapter(presenter::onMovieClicked)

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, parent, false)

        presenter.onCreate(this)
        recycler.adapter = adapter

        return rootView
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateData(movies: List<Movie>) {
        adapter.movies = movies
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


}