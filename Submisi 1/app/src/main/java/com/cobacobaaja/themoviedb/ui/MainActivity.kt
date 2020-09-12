package com.cobacobaaja.themoviedb.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.model.Results
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        loadMovie()
        Log.e("Device","OnCreate")
    }
    private fun initRv() {
        adapterMovie = MovieAdapter()
        adapterMovie.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapterMovie
    }
    private fun loadMovie() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        search_movie.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                movieViewModel.setSearchMovie(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
        movieViewModel.getMovie().observe(this, Observer{items ->
            if (items !=null){
                adapterMovie.setData(items.results)
            } else {
                Toast.makeText(this, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })
        movieViewModel.setMovie()
    }

}
