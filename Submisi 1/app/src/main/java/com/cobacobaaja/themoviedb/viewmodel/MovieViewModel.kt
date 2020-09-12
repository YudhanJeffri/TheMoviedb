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

    fun setMovie() {
        if (listWeathers.value == null){
            // request API
            // api_key 2e5975109a9b9ccbc9739e60f6e8ed65
            val retroInstance = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
            val call = retroInstance.getMovie(apiKey = api_key)
            call.enqueue(object : Callback<MovieResponses>{
                override fun onResponse(call: Call<MovieResponses>, response: Response<MovieResponses>) {
                    if (response.isSuccessful) {
                        listWeathers.postValue(response.body())
                    } else {
                        listWeathers.postValue(null)
                    }
                }
                override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                    listWeathers.postValue(null)
                }
            })
        }
    }
    fun setSearchMovie(movies : String) {
        // request API
        val retroInstance = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
        val call = retroInstance.getSearchMovie(movies,apiKey = api_key)
        call.enqueue(object : Callback<MovieResponses>{
            override fun onResponse(call: Call<MovieResponses>, response: Response<MovieResponses>) {
                listWeathers.postValue(response.body())
            }
            override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                listWeathers.postValue(null)
            }
        })
    }
    fun getMovie(): MutableLiveData<MovieResponses> {
        return listWeathers
    }
}