package com.liebersonsantos.tmdbproject.ui.fragment.adventurefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.liebersonsantos.tmdbproject.BuildConfig
import com.liebersonsantos.tmdbproject.R
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.utils.SharedPreference
import com.liebersonsantos.tmdbproject.ui.fragment.adventurefragment.viewmodel.AdventureViewModel
import kotlinx.android.synthetic.main.fragment_adventure.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdventureFragment : Fragment() {

    var listFavoriteMovie = listOf<FavoriteMovies>()

    lateinit var userEmail: String

    private val viewModel: AdventureViewModel by lazy {
        ViewModelProvider(this).get(AdventureViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_adventure, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            var sharedPreference = SharedPreference(fragmentActivity)
            sharedPreference.getData("USER")?.let { email ->
                userEmail = email
            }

            viewModel.getFavoriteMovie(userEmail).observe(fragmentActivity, Observer { listMovie ->
                listMovie?.let { movies ->
                    listFavoriteMovie = movies
                }
            })

            viewModel.movieLiveData.observe(fragmentActivity, Observer {
                it?.let {movieList ->
                    with(recyclerViewAdventure) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = AdventureAdapter(
                            movieList,
                            listFavoriteMovie,
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
                                    fragmentActivity,
                                    "Filme ${movie.originalTitle} inserido com sucesso",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            { deleteMovie ->
                                GlobalScope.launch {
                                    viewModel.deleteMovie(
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
                                    fragmentActivity,
                                    "Filme ${deleteMovie.originalTitle} deletado com sucesso",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                    }
                }
            })
        }
        viewModel.getMoviesByGenres(BuildConfig.API_KEY, "pt-BR", false, 12)
    }

}
