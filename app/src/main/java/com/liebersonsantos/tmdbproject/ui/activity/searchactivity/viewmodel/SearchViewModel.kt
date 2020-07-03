package com.liebersonsantos.tmdbproject.ui.activity.searchactivity.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.model.response.SearchMovieResponse
import com.liebersonsantos.tmdbproject.data.model.result.SearchMovieResults
import com.liebersonsantos.tmdbproject.data.repository.FavoriteMovieRepository
import com.liebersonsantos.tmdbproject.data.repository.SearchMovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SearchMovieRepository()
    private val favoriteRepository = FavoriteMovieRepository(application)

    val movieLiveData: MutableLiveData<List<SearchMovieResults>> = MutableLiveData()

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) = favoriteRepository.insertMovie(favoriteMovies)

    suspend fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies) = favoriteRepository.deleteFavoriteMovie(favoriteMovies)

    fun getFavoriteMovie(userEmail: String) : LiveData<List<FavoriteMovies>> = favoriteRepository.getFavoriteMovie(userEmail)

    fun searchMovies(query: String, apiKey: String, language: String, includeAdult: Boolean) {

        repository.searchMovies(query, apiKey, language, includeAdult)
            .enqueue(object : Callback<SearchMovieResponse> {

                override fun onResponse(call: Call<SearchMovieResponse>,
                                        response: Response<SearchMovieResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let { movieResponse ->
                                movieLiveData.value = movieResponse.searchMovie
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.d(SearchViewModel::class.java.simpleName, "ERROR: ${t.message}")
                }
            })
    }
}