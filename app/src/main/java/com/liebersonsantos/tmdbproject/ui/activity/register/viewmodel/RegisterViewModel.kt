package com.liebersonsantos.tmdbproject.ui.activity.register.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import com.liebersonsantos.tmdbproject.data.database.modeldb.User
import com.liebersonsantos.tmdbproject.data.repository.UserRepository

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(application)

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    fun verifyPhoneNumber(phone: EditText) : String = if (TextUtils.isEmpty(phone.text.toString())) phone.text.toString() else ""

    fun isValid(name: EditText, email: EditText, password: EditText): Int {
        return if (TextUtils.isEmpty(name.text.toString()) && TextUtils.isEmpty(email.text.toString()) && TextUtils.isEmpty(password.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(name.text.toString())) {
            return 1
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() || TextUtils.isEmpty(email.text.toString())) {
            return 2
        } else if (TextUtils.isEmpty(password.text.toString())) {
            return 3
        } else -1
    }
}