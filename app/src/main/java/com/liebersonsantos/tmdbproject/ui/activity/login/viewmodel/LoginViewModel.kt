package com.liebersonsantos.tmdbproject.ui.activity.login.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.liebersonsantos.tmdbproject.data.database.modeldb.User
import com.liebersonsantos.tmdbproject.data.repository.UserRepository

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(application)

    fun getUserDB(email: String, password: String) : LiveData<User> {
        return repository.getUserDB(email, password)
    }

    fun isValid(email: EditText, password: EditText): Int {
        return if (TextUtils.isEmpty(email.text.toString()) && TextUtils.isEmpty(password.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(password.text.toString())) {
            return 1
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() || TextUtils.isEmpty(email.text.toString())) {
            return 2
        } else -1
    }
}