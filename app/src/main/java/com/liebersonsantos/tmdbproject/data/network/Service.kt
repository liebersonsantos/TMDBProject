package com.liebersonsantos.tmdbproject.data.network

import com.liebersonsantos.tmdbproject.data.model.response.MovieResponse
import com.liebersonsantos.tmdbproject.data.model.response.SearchMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("discover/movie")
    fun getMoviesByGenres(@Query("api_key") apiKey: String,
                          @Query("language") language: String,
                          @Query("include_adult") includeAdult: Boolean,
                          @Query("with_genres") withGenres: Int): Call<MovieResponse>


    @GET("search/movie")
    fun searchMovies(@Query("query") query: String,
                     @Query("api_key") apiKey: String,
                     @Query("language") language: String,
                     @Query("include_adult") includeAdult: Boolean): Call<SearchMovieResponse>
}