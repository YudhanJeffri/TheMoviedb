package com.cobacobaaja.themoviedb.model


import com.google.gson.annotations.SerializedName

data class MovieResponses(
    val page: Int,

    var results: List<Results>,

    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int


)