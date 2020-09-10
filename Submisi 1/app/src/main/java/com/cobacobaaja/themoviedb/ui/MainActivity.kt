package com.cobacobaaja.themoviedb.ui

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.adapter.SearchMovieAdapter
import com.cobacobaaja.themoviedb.api.ClientService
import com.cobacobaaja.themoviedb.api.RetrofitClient
import com.cobacobaaja.themoviedb.model.MovieResponses
import com.cobacobaaja.themoviedb.model.Results
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import com.cobacobaaja.themoviedb.viewmodel.SearchMovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movietv_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var searchMovieViewModel: SearchMovieViewModel
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        loadMovieRetrofit()
        loadSearchMovie()
    }
    private fun initRv() {
        adapterMovie = MovieAdapter()
        adapterMovie.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapterMovie
    }

    private fun loadSearchMovie() {
        searchMovieViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(SearchMovieViewModel::class.java)
        search_movie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchMovieViewModel.setSearchMovie(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
        searchMovieViewModel.getSearchMovie().observe(this, Observer<MovieResponses>{
            if (it !=null){
                adapterMovie.setData(it.results)
            } else {
                Toast.makeText(this, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })

    }

     private fun loadMovieRetrofit(){
        movieViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        movieViewModel.getWeathers().observe(this, Observer<MovieResponses>{
            if (it !=null){
                adapterMovie.setData(it.results)
            } else {
                Toast.makeText(this, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })
        movieViewModel.setWeather()
    }
}
