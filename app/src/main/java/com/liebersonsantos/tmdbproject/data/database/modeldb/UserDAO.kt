package com.liebersonsantos.tmdbproject.data.database.modeldb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String) : LiveData<User>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String) : LiveData<User>
}