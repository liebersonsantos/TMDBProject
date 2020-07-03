package com.liebersonsantos.tmdbproject.data.model.response

import com.google.gson.annotations.SerializedName
import com.liebersonsantos.tmdbproject.data.model.result.MovieResults

data class MovieResponse(

    @SerializedName("results")
    val genreResults: List<MovieResults>
)