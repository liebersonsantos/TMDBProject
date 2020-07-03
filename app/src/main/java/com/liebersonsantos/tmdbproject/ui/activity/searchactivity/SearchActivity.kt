package com.liebersonsantos.tmdbproject.ui.activity.searchactivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.liebersonsantos.tmdbproject.BuildConfig
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.utils.SharedPreference
import com.liebersonsantos.tmdbproject.ui.activity.baseactivity.BaseActivity
import com.liebersonsantos.tmdbproject.ui.activity.searchactivity.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchActivity : BaseActivity() {

    var listFavoriteMovies = listOf<FavoriteMovies>()

    lateinit var userEmail: String

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupToolbar(toolbarMovie, R.string.txt_search, true)

        var sharedPreference = SharedPreference(this)
        sharedPreference.getData("USER")?.let {email ->
            userEmail = email
        }

        viewModel.getFavoriteMovie(userEmail).observe(this, Observer {listMovie ->
            listMovie?.let {movies ->
                listFavoriteMovies = movies
            }
        })

        viewModel.movieLiveData.observe(this, Observer { searchMovieResults ->
            searchMovieResults?.let { movieList ->
                with(recyclerViewSearch) {
                    layoutManager = GridLayoutManager(this@SearchActivity, 2)
                    adapter = SearchAdapter(
                        movieList,
                        listFavoriteMovies,
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
                                this@SearchActivity,
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
                                this@SearchActivity,
                                "Filme ${deleteMovie.originalTitle} deletado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                }
            }
        })

        svSearchMovie.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { query ->
                    viewModel.searchMovies(query, BuildConfig.API_KEY, "pt-BR", false)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("QUERY", "Palavra enviada para request -> $newText")
                return true
            }

        })

    }
}
