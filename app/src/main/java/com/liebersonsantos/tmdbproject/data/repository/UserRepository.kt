package com.liebersonsantos.tmdbproject.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.liebersonsantos.tmdbproject.data.database.TmdbDataBase
import com.liebersonsantos.tmdbproject.data.database.modeldb.User
import com.liebersonsantos.tmdbproject.data.database.modeldb.UserDAO

class UserRepository(context: Context) {
    private val userDAO: UserDAO by lazy {
        TmdbDataBase.getDb(context).userDao()
    }

    suspend fun insertUser(user: User) {
        userDAO.insertUser(user)
    }

    fun getUserDB(email: String, password: String): LiveData<User> = userDAO.getUser(email, password)

    fun getUserByEmail(email: String) : LiveData<User> = userDAO.getUserByEmail(email)
}