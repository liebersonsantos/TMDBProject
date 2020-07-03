package com.liebersonsantos.tmdbproject.data.repository

import com.liebersonsantos.tmdbproject.data.model.response.MovieResponse
import com.liebersonsantos.tmdbproject.data.network.ApiService
import retrofit2.Call

class MovieRepository {

    fun getMoviesByGenres(apiKey: String, language: String, includeAdult: Boolean, withGenres: Int) : Call<MovieResponse> {
        return ApiService.service.getMoviesByGenres(apiKey, language, includeAdult, withGenres)
    }
}