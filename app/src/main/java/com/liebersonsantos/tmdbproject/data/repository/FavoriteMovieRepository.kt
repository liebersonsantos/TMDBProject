package com.liebersonsantos.tmdbproject.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.liebersonsantos.tmdbproject.data.database.TmdbDataBase
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovieDAO
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies

class FavoriteMovieRepository(context: Context) {
    private val favoriteMovieDAO: FavoriteMovieDAO by lazy {
        TmdbDataBase.getDb(context).favoriteMovieDao()
    }

    suspend fun insertMovie(favoriteMovies: FavoriteMovies){
        favoriteMovieDAO.insertMovie(favoriteMovies)
    }

    fun getFavoriteMovie(userEmail: String): LiveData<List<FavoriteMovies>> = favoriteMovieDAO.getFavoriteMovie(userEmail)

    suspend fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies){
        favoriteMovieDAO.deleteFavoriteMovie(favoriteMovies)
    }
}