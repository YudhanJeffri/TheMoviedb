package com.cobacobaaja.themoviedb.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.model.Results
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var list = ArrayList<Results>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        loadMovie()
    }

    private fun initRv() {
        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapter
        rvMovie.setHasFixedSize(true)
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