package com.cobacobaaja.themoviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobacobaaja.themoviedb.api.ClientService
import com.cobacobaaja.themoviedb.api.RetrofitClient
import com.cobacobaaja.themoviedb.data.Constant
import com.cobacobaaja.themoviedb.model.MovieResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvViewModel : ViewModel() {
    val listWeathers = MutableLiveData<MovieResponses>()

    fun getTVs() {
        if (listWeathers.value == null) {
            // request API
            val retroInstance =
                RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
            val call = retroInstance.getTv(apiKey = Constant.api_key)
            call.enqueue(object : Callback<MovieResponses> {
                override fun onResponse(
                    call: Call<MovieResponses>,
                    response: Response<MovieResponses>
                ) {
                    listWeathers.postValue(response.body())
                }

                override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                    listWeathers.postValue(null)
                }
            })
        }
    }


    fun setSearchTv(movies : String) {
        // request API
        val retroInstance =
            RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
        val call = retroInstance.getSearchTv(movies, apiKey = Constant.api_key)
        call.enqueue(object : Callback<MovieResponses> {
            override fun onResponse(
                call: Call<MovieResponses>,
                response: Response<MovieResponses>
            ) {
                listWeathers.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                listWeathers.postValue(null)
            }
        })
    }
    fun getTv(): MutableLiveData<MovieResponses> {
        return listWeathers
    }
}