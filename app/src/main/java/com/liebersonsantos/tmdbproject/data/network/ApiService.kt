package com.liebersonsantos.tmdbproject.data.network

import com.google.gson.GsonBuilder
import com.liebersonsantos.tmdbproject.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private fun initRetrofit() : Retrofit {
        val gson = GsonBuilder().setLenient().create() //alguns jsons podem dar erro na parse.

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val service: Service = initRetrofit().create(Service::class.java)
}