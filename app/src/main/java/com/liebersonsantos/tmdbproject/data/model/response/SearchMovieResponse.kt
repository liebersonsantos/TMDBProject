package com.liebersonsantos.tmdbproject.data.model.response

import com.google.gson.annotations.SerializedName
import com.liebersonsantos.tmdbproject.data.model.result.SearchMovieResults

data class SearchMovieResponse(

    @SerializedName("results")
    val searchMovie: List<SearchMovieResults>
)