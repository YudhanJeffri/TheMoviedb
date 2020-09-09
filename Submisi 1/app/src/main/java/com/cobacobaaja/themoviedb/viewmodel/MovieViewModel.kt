package com.cobacobaaja.themoviedb.viewmodel

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobacobaaja.themoviedb.api.ClientService
import com.cobacobaaja.themoviedb.api.RetrofitClient
import com.cobacobaaja.themoviedb.model.MovieResponses
import com.cobacobaaja.themoviedb.model.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    val listWeathers = MutableLiveData<ArrayList<Results>>()

    fun setWeather() {
        // request API
        // api_key 2e5975109a9b9ccbc9739e60f6e8ed65
        val retroInstance = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
        val call = retroInstance.getMovie(apiKey = "2e5975109a9b9ccbc9739e60f6e8ed65")
        call.enqueue(object : retrofit2.Callback<MovieResponses>{
            override fun onResponse(call: Call<MovieResponses>, response: Response<MovieResponses>) {
                if(response.isSuccessful) {
                    listWeathers.postValue(response.body()?.results)
                } else {
                    listWeathers.postValue(null)
                }
            }
            override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                listWeathers.postValue(null)
            }
        })
    }
    fun getWeathers(): LiveData<ArrayList<Results>> {
        return listWeathers
    }
}