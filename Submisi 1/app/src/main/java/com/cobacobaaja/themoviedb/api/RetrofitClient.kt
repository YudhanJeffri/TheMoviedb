package com.cobacobaaja.themoviedb.api

import com.cobacobaaja.themoviedb.cobacoba.Retro
import com.cobacobaaja.themoviedb.data.Constant.BaseUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    val retrofitinstance:Retrofit?
    get() {
        if(retrofit ==null){
            retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}