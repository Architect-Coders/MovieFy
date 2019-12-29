package com.moviefy.ui.releaseFilms

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviefy.R
import com.moviefy.model.Movie
import com.moviefy.ui.common.basicDiffUtil
import com.moviefy.ui.common.inflate
import com.moviefy.ui.common.loadImage
import kotlinx.android.synthetic.main.movie_poster.view.*

class MoviesAdapter(private val listener: (Movie, Boolean, Boolean) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var movies: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.movie_poster, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.moviePoster.setOnClickListener {
            listener(movie, false, true)
        }

        holder.itemView.imageViewMovieListItemFavorite.setOnClickListener {
            holder.itemView.imageViewMovieListItemFavorite.isActivated = !holder.itemView.imageViewMovieListItemFavorite.isActivated
            listener(movie, holder.itemView.imageViewMovieListItemFavorite.isActivated, false)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {

            movie.posterPath?.let {
                itemView.moviePoster.loadImage(movie.posterPath)
            }

            if(checkIfNeedShowStar(movie.voteAverage)) {
                itemView.start.visibility = View.VISIBLE
                itemView.moviePuntuation.text = movie.voteAverage.toString()
            }else{
                itemView.start.visibility = View.INVISIBLE
            }
        }

        private fun checkIfNeedShowStar(average: Double) : Boolean{
            return average >= 7.0
        }
    }
}