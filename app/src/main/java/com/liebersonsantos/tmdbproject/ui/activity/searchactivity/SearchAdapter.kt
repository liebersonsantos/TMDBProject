package com.liebersonsantos.tmdbproject.ui.activity.searchactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liebersonsantos.tmdbproject.BuildConfig
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.model.result.SearchMovieResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class SearchAdapter(val searchMovies: List<SearchMovieResults>,
                    val checkedMovie: List<FavoriteMovies>,
                    val favoriteClickListener: ((movie: SearchMovieResults) -> Unit),
                    val deleteClickListener: ((movie: SearchMovieResults) -> Unit))
    : RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return SearchAdapterViewHolder(
            itemView,
            favoriteClickListener,
            deleteClickListener
        )
    }

    override fun getItemCount() = searchMovies.count()

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(searchMovies[position], checkedMovie)
    }

    class SearchAdapterViewHolder(itemView: View,
                                  private val favoriteClickListener: (movie: SearchMovieResults) -> Unit,
                                  private val deleteClickListener: (movie: SearchMovieResults) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val imageViewFavoriteMovie = itemView.img_favorite_movie
        private val imageViewFavoriteRed = itemView.img_favorite_red
        private val picasso = Picasso.get()

        fun bind(movie: SearchMovieResults, checkedMovie: List<FavoriteMovies>) {
            textNameMovie.text = movie.originalTitle
            textVoteAverage.text = movie.voteAverage.toString()
            textReleaseDate.text = movie.releaseDate

            for (i in checkedMovie) {
                when {
                    movie.id.equals(i.id) -> imageViewFavoriteRed.visibility = View.VISIBLE
                }
            }

            movie.posterPath.let {
                picasso.load("""${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}""")
                    .into(imageViewMovie)
            }

            imageViewFavoriteMovie.setOnClickListener {
                favoriteClickListener.invoke(movie)
                imageViewFavoriteMovie.visibility = View.GONE
                imageViewFavoriteRed.visibility = View.VISIBLE
            }

            imageViewFavoriteRed.setOnClickListener {
                deleteClickListener.invoke(movie)
                imageViewFavoriteMovie.visibility = View.VISIBLE
                imageViewFavoriteRed.visibility = View.GONE
            }
        }
    }
}