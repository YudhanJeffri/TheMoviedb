package com.cobacobaaja.themoviedb.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.adapter.SectionsPagerAdapter
import com.cobacobaaja.themoviedb.model.Results
import com.cobacobaaja.themoviedb.ui.fragment.MovieFragment
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.movietv_items.*
import kotlinx.android.synthetic.main.txt_layout.*
import kotlinx.android.synthetic.main.txt_layout.view.*
import org.json.JSONObject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var adapterMovie: MovieAdapter
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        Log.e("Device", "OnCreate")
        setSupportActionBar(toolbar)
        setUpViewPager()
    }
    private fun setUpViewPager(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.search_movie)
        val search_movie = searchItem?.actionView as SearchView
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        search_movie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                movieViewModel.setSearchMovie(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
        movieViewModel.getMovie().observe(this, Observer { items ->
            if (items != null) {
                adapterMovie.setData(items.results)
            } else {
                Toast.makeText(this, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("WrongConstant")
    private fun initRv() {
        adapterMovie = MovieAdapter()
        adapterMovie.notifyDataSetChanged()
        //rvMovie.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapterMovie
    }
}
