package com.cobacobaaja.themoviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cobacobaaja.themoviedb.R

class DetailActivity : AppCompatActivity() {
    val EXTRA_MOVIE = "extra_movie"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        loadMovie()
    }

    private fun loadMovie() {
        TODO("Not yet implemented")
    }
}