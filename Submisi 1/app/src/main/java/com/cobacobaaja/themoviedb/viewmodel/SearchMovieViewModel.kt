package com.cobacobaaja.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobacobaaja.themoviedb.api.ClientService
import com.cobacobaaja.themoviedb.api.RetrofitClient
import com.cobacobaaja.themoviedb.model.MovieResponses
import retrofit2.Call
import retrofit2.Response

class SearchMovieViewModel : ViewModel() {
    val listWeathers = MutableLiveData<MovieResponses>()

    fun setSearchMovie(movies : String) {
        // request API
        // api_key 2e5975109a9b9ccbc9739e60f6e8ed65
        val retroInstance = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
        val call = retroInstance.getSearchMovie(movies,apiKey = "2e5975109a9b9ccbc9739e60f6e8ed65")
        call.enqueue(object : retrofit2.Callback<MovieResponses>{
            override fun onResponse(call: Call<MovieResponses>, response: Response<MovieResponses>) {
                listWeathers.postValue(response.body())
            }
            override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                listWeathers.postValue(null)
            }
        })
    }
    fun getSearchMovie(): LiveData<MovieResponses> {
        return listWeathers
    }
}