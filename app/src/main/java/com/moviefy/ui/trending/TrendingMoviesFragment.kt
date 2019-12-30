package com.moviefy.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.data.repository.DateRepository
import com.moviefy.R
import com.moviefy.data.database.Movie
import com.e.data.repository.MoviesRepository
import com.e.data.repository.RegionRepository
import com.e.usecases.FindTrendingMovies
import com.moviefy.data.AndroidPermissionChecker
import com.moviefy.data.PlayServicesLocationDatasource
import com.moviefy.data.database.FavouriteDataSource
import com.moviefy.data.server.MovieDataSource
import com.moviefy.ui.common.app
import com.moviefy.ui.common.showToast
import com.moviefy.ui.navigator.Navigator
import com.moviefy.ui.releaseFilms.MoviesAdapter
import kotlinx.android.synthetic.main.trending_movies_fragment.*

class TrendingMoviesFragment : Fragment(), TrendingFilmsPresenter.View {

    private var presenter: TrendingFilmsPresenter? = null
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.trending_movies_fragment, parent, false)

        presenter = TrendingFilmsPresenter(FindTrendingMovies(MoviesRepository(
            activity!!.app.getString(R.string.apy_key),
            MovieDataSource(),
            DateRepository(),
            RegionRepository(
                PlayServicesLocationDatasource(activity!!.app),
                AndroidPermissionChecker(activity!!.app)
            )
        )), FavouriteDataSource(activity!!.app.database))

        presenter?.let { presenter ->
            presenter.onCreate(this)
            adapter = MoviesAdapter{movie, isSave, isOpenDetail ->
                presenter.onMovieClicked(movie, isSave, isOpenDetail)
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

    override fun saveInFavourites() {
        activity?.showToast(getString(R.string.movie_save_favourite))
    }

    override fun removeFromFavourites() {
        activity?.showToast(getString(R.string.movie_remove_favourite))
    }
}