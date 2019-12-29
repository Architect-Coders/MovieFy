package com.moviefy.ui.navigator

import androidx.fragment.app.FragmentActivity
import com.moviefy.data.database.Movie
import com.moviefy.ui.common.startActivity
import com.moviefy.ui.detailMovie.DetailMovie

class Navigator {

    object MovieDetail {
        fun openDetail(activity: FragmentActivity, movie: Movie) {
            activity.startActivity<DetailMovie> {
                putExtra(DetailMovie.MOVIE, movie)
            }
        }
    }
}