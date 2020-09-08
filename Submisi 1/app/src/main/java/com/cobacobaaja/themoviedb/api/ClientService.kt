package com.cobacobaaja.themoviedb.api

import com.cobacobaaja.themoviedb.model.MovieResponses
import com.cobacobaaja.themoviedb.model.Results
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {
    @GET("discover/movie")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<Results>
}