package com.moviefy.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moviefy.R
import com.moviefy.data.database.Movie
import com.moviefy.ui.navigator.Navigator
import com.moviefy.ui.releaseFilms.MoviesAdapter
import kotlinx.android.synthetic.main.favourite_fragment.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class FavouriteFragment : Fragment(), FavouritePresenter.View {

    private val presenter: FavouritePresenter by inject { parametersOf(this) }
//    private var presenter: FavouritePresenter? = null
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.favourite_fragment, parent, false)

//        presenter = FavouritePresenter(
//            GetFavouritesMovies(
//                FavouriteRepository(
//                    FavouriteDataSource(activity!!.app.database)
//                )
//            )
//        )


        presenter.let { presenter ->
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
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun navigateTo(movie: Movie) {
        activity?.let {activity ->
            Navigator.MovieDetail.openDetail(activity, movie)
        }
    }
}