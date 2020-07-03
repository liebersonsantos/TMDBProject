package com.liebersonsantos.tmdbproject.data.repository

import com.liebersonsantos.tmdbproject.data.model.response.SearchMovieResponse
import com.liebersonsantos.tmdbproject.data.network.ApiService
import retrofit2.Call

class SearchMovieRepository {

    fun searchMovies(query: String, apiKey: String, language: String, includeAdult: Boolean) : Call<SearchMovieResponse> {
        return ApiService.service.searchMovies(query, apiKey, language, includeAdult)
    }

}