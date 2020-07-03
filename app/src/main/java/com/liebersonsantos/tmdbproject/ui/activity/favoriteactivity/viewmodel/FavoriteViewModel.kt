package com.liebersonsantos.tmdbproject.ui.activity.favoriteactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.repository.FavoriteMovieRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteMovieRepository(application)

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) = repository.insertMovie(favoriteMovies)

    suspend fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies) = repository.deleteFavoriteMovie(favoriteMovies)

    fun getFavoriteMovie(userEmail: String): LiveData<List<FavoriteMovies>> = repository.getFavoriteMovie(userEmail)
}