package com.liebersonsantos.tmdbproject.ui.activity.favoriteactivity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.utils.SharedPreference
import com.liebersonsantos.tmdbproject.ui.activity.baseactivity.BaseActivity
import com.liebersonsantos.tmdbproject.ui.activity.favoriteactivity.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity : BaseActivity() {

    lateinit var userEmail: String

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setupToolbar(toolbarMovie, R.string.txt_favorite_movies, true)

        val sharedPreference = SharedPreference(this)
        sharedPreference.getData("USER")?.let { email ->
            userEmail = email
        }

        viewModel.getFavoriteMovie(userEmail).observe(this, Observer { favoriteMovies ->
            favoriteMovies?.let { favoriteList ->
                with(recyclerViewFavorite) {
                    layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
                    setHasFixedSize(true)
                    adapter = FavoriteAdapter(
                        favoriteList,
                        { movie ->
                            GlobalScope.launch {
                                viewModel.insertMovie(
                                    FavoriteMovies(
                                        movie.id,
                                        userEmail,
                                        movie.originalTitle,
                                        movie.voteAverage,
                                        movie.genreIds,
                                        movie.overview,
                                        movie.posterPath,
                                        movie.releaseDate
                                    )
                                )
                            }
                            Toast.makeText(
                                this@FavoriteActivity,
                                "Filme ${movie.originalTitle} inserido com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        { deleteMovie ->
                            GlobalScope.launch {
                                viewModel.deleteFavoriteMovie(
                                    FavoriteMovies(
                                        deleteMovie.id,
                                        userEmail,
                                        deleteMovie.originalTitle,
                                        deleteMovie.voteAverage,
                                        deleteMovie.genreIds,
                                        deleteMovie.overview,
                                        deleteMovie.posterPath,
                                        deleteMovie.releaseDate
                                    )
                                )
                            }
                            Toast.makeText(
                                this@FavoriteActivity,
                                "Filme ${deleteMovie.originalTitle} deletado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                }
            }
        })
    }
}