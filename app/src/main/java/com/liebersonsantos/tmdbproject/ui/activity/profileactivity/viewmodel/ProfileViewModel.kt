package com.liebersonsantos.tmdbproject.ui.activity.profileactivity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.liebersonsantos.tmdbproject.data.database.modeldb.User
import com.liebersonsantos.tmdbproject.data.repository.UserRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    fun getUserByEmail(email: String) : LiveData<User> = repository.getUserByEmail(email)

}