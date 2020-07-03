package com.liebersonsantos.tmdbproject.ui.fragment.action.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.model.response.MovieResponse
import com.liebersonsantos.tmdbproject.data.model.result.MovieResults
import com.liebersonsantos.tmdbproject.data.repository.FavoriteMovieRepository
import com.liebersonsantos.tmdbproject.data.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActionViewModel(application: Application): AndroidViewModel(application) {

    val favoriteRepository = FavoriteMovieRepository(application)
    val repository = MovieRepository()

    val movieLiveData: MutableLiveData<List<MovieResults>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) = favoriteRepository.insertMovie(favoriteMovies)

    suspend fun deleteMovie(favoriteMovies: FavoriteMovies) = favoriteRepository.deleteFavoriteMovie(favoriteMovies)

    fun getFavoriteMovie(userEmail: String) : LiveData<List<FavoriteMovies>> = favoriteRepository.getFavoriteMovie(userEmail)

    fun getMoviesByGenres(apiKey: String, language: String, includeAdult: Boolean, withGenres: Int) {
        isLoading.value = true
        repository.getMoviesByGenres(apiKey, language, includeAdult, withGenres).enqueue(object :
            Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                when {
                    response.isSuccessful -> {
                        isLoading.value = false
                        response.body()?.let { genreResponse ->
                            movieLiveData.value = genreResponse.genreResults
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading.value = false
                Log.d(ActionViewModel::class.java.simpleName, "ERROR: ${t.message}")
            }
        })
    }
}