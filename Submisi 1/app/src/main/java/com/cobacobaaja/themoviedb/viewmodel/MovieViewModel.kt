package com.cobacobaaja.themoviedb.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobacobaaja.themoviedb.api.ClientService
import com.cobacobaaja.themoviedb.api.RetrofitClient
import com.cobacobaaja.themoviedb.data.Constant.api_key
import com.cobacobaaja.themoviedb.model.MovieResponses
import com.cobacobaaja.themoviedb.model.Results
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    val listWeathers = MutableLiveData<MovieResponses>()

    fun loadPagerMovie(){
        // request API
        val retroInstance =
            RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
        val call = retroInstance.getPlayingMovie(apiKey = api_key)
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

    fun setSearchMovie(movies : String) {
        if (listWeathers.value == null) {
            // request API
            val retroInstance =
                RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
            val call = retroInstance.getSearchMovie(movies, apiKey = api_key)
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
    fun getMovie(): MutableLiveData<MovieResponses> {
        return listWeathers
    }
}