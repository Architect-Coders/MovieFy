package com.moviefy.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.data.repository.DateRepository
import com.e.data.repository.FavouriteRepository
import com.e.data.repository.MoviesRepository
import com.e.data.repository.RegionRepository
import com.e.usecases.FindTrendingMovies
import com.e.usecases.GetFavouritesMovies
import com.moviefy.R
import com.moviefy.data.AndroidPermissionChecker
import com.moviefy.data.PlayServicesLocationDatasource
import com.moviefy.data.database.FavouriteDataSource
import com.moviefy.data.database.FilmDatabase
import com.moviefy.data.database.Movie
import com.moviefy.data.server.MovieDataSource
import com.moviefy.ui.common.app
import com.moviefy.ui.common.showToast
import com.moviefy.ui.navigator.Navigator
import com.moviefy.ui.releaseFilms.MoviesAdapter
import com.moviefy.ui.trending.TrendingFilmsPresenter
import kotlinx.android.synthetic.main.favourite_fragment.*


class FavouriteFragment : Fragment(), FavouritePresenter.View {

    private var presenter: FavouritePresenter? = null
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.favourite_fragment, parent, false)

        presenter = FavouritePresenter(
            GetFavouritesMovies(
                FavouriteRepository(
                    FavouriteDataSource(activity!!.app.database)
                )
            )
        )


        presenter?.let { presenter ->
            presenter.onCreate(this)
            adapter = MoviesAdapter{movie, isSave, isOpenDetail ->
                presenter.onMovieClicked(movie, isSave, isOpenDetail)
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerFavouriteMovies.adapter = adapter
    }

    override fun showProgress() {
        progressBarFavouriteMovies.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBarFavouriteMovies.visibility = View.GONE
    }

    override fun updateData(movies: List<Movie>) {
        adapter?.movies = movies
    }

    override fun emptyFavourites() {
        txEmptyFavourite.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    override fun navigateTo(movie: Movie) {
        activity?.let {activity ->
            Navigator.MovieDetail.openDetail(activity, movie)
        }
    }
}