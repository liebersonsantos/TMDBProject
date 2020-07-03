package com.liebersonsantos.tmdbproject.data.database.modeldb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteMovieDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(favoriteMovies: FavoriteMovies)

    @Query("SELECT * FROM favorite_movie WHERE user_email = :userEmail")
    fun getFavoriteMovie(userEmail: String) : LiveData<List<FavoriteMovies>>

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies)
}