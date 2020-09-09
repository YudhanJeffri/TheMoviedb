package com.cobacobaaja.themoviedb.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.api.ClientService
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
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        loadMovieRetrofit()
    }

    private fun initRv() {
        adapterMovie = MovieAdapter()
        adapterMovie.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapterMovie
    }
    private fun loadMovieRetrofit(){
        movieViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        movieViewModel.setWeather()
        movieViewModel.getWeathers().observe(this,Observer{itemsMovie->
            if (itemsMovie != null){
                adapterMovie.setData(itemsMovie)
            }
        })
    }
}
