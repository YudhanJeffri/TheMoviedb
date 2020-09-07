package com.cobacobaaja.themoviedb.model


import com.google.gson.annotations.SerializedName

data class Results(
    var id: Int? = null,
    var overview: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    var title: String? = null,
    var release_date: String? = null,
    var popularity: String? = null,
    var vote_count: String? = null,
    var vote_average: String? = null
)