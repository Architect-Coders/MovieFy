package com.moviefy.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moviefy.R
import com.moviefy.model.Movie
import com.moviefy.model.MoviesRepository
import com.moviefy.ui.navigator.Navigator
import com.moviefy.ui.releaseFilms.MoviesAdapter
import com.moviefy.ui.releaseFilms.ReleaseFilmsPresenter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.trending_movies_fragment.*

class TrendingMoviesFragment : Fragment(), TrendingFilmsPresenter.View {

    private var presenter: TrendingFilmsPresenter? = null
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.trending_movies_fragment, parent, false)

        presenter = TrendingFilmsPresenter(MoviesRepository(activity!!))

        presenter?.let { presenter ->
            presenter.onCreate(this)
            adapter = MoviesAdapter{movie, isSave ->
                presenter.onMovieClicked(movie, isSave)
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerPopularMovies.adapter = adapter
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun updateData(movies: List<Movie>) {
        adapter?.movies = movies
    }

    override fun showProgress() {
        progressBarPopularMovies.visibility = View.VISIBLE
    }

    override fun navigateTo(movie: Movie) {
        activity?.let {activity ->
            Navigator.MovieDetail.openDetail(activity, movie)
        }
    }

    override fun hideProgress() {
        progressBarPopularMovies.visibility = View.INVISIBLE
    }
}