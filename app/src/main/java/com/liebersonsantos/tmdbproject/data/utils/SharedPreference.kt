package com.liebersonsantos.tmdbproject.data.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val USER = "USER"

    private val sharedPref: SharedPreferences =  context.getSharedPreferences(USER, Context.MODE_PRIVATE)

    fun getData(user: String): String?{
        return sharedPref.getString(user, "")
    }

    fun saveData(user: String, email: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(user, email)
        editor.apply()

    }

}