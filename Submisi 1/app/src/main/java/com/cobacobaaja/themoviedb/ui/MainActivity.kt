package com.cobacobaaja.themoviedb.ui

import android.R.attr.data
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.api.ClientService
import com.cobacobaaja.themoviedb.api.RetrofitClient
import com.cobacobaaja.themoviedb.model.MovieResponses
import com.cobacobaaja.themoviedb.model.Results
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movietv_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    var str:String = ""
    private var list = ArrayList<Results>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRv()

        //loadMovie()
        getRetrofitAPI()
    }

    private fun initRv() {
        adapter = MovieAdapter(list)
        adapter.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapter
        rvMovie.setHasFixedSize(true)
    }
    // apikey = 2e5975109a9b9ccbc9739e60f6e8ed65
    fun getRetrofitAPI(){
        val service = RetrofitClient.retrofitinstance?.create(ClientService::class.java)
        val call = service?.getMovie(apiKey = "2e5975109a9b9ccbc9739e60f6e8ed65")
        call?.enqueue(object : Callback<Results>{
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Toast.makeText(applicationContext, "Error reading JSON", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<Results>, response: Response<Results>) {
                val body = response?.body()
                textmovietv.text = body?.title
                response.body()?.let { list.addAll(listOf(it)) }
                val adapter = MovieAdapter(list)
                rvMovie.adapter = adapter
            }

        })



    }

    private fun loadMovie() {
        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        movieViewModel.setMovie()
        movieViewModel.getMovie().observe(this, Observer { list ->
            if (list != null){
                adapter.setData(list)
            }
        })
    }

}
