package com.cobacobaaja.themoviedb.api

import com.cobacobaaja.themoviedb.model.MovieResponses
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {
    @GET("discover/movie?api_key=2e5975109a9b9ccbc9739e60f6e8ed65")
    fun getMovie(): Call<MovieResponses>

}