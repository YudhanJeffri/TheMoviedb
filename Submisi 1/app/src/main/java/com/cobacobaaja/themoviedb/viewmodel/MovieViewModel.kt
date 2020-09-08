package com.cobacobaaja.themoviedb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobacobaaja.themoviedb.data.Constant.BaseUrl
import com.cobacobaaja.themoviedb.model.Results
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MovieViewModel : ViewModel() {
    val listMovie = MutableLiveData<ArrayList<Results>>()

    internal fun setMovie(){
        val client = AsyncHttpClient()
        val listItems = ArrayList<Results>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=2e5975109a9b9ccbc9739e60f6e8ed65"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")
                    for (i in 0 until list.length()) {
                        val items = list.getJSONObject(i)
                        val movieItems = Results()
                        movieItems.id = items.getInt("id")
                        movieItems.overview = items.getString("overview")
                        movieItems.posterPath = items.getString("poster_path")
                        movieItems.title = items.getString("title")
                        movieItems.release_date = items.getString("release_date")
                        movieItems.popularity = items.getString("popularity")
                        movieItems.vote_average = items.getString("vote_average")
                        movieItems.vote_count = items.getString("vote_count")
                        listItems.add(movieItems)
                    }
                    listMovie.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    internal fun getMovie(): LiveData<ArrayList<Results>> {
        return listMovie
    }
}