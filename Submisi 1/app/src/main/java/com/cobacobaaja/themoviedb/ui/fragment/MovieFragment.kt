package com.cobacobaaja.themoviedb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MovieAdapter
import com.cobacobaaja.themoviedb.adapter.MoviePagerAdapter
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_now_showing.*

class MovieFragment : Fragment() {

    private lateinit var adapterMovie: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie, container, false)
        adapterMovie = MovieAdapter()
        adapterMovie.notifyDataSetChanged()
        var mainRV = v.findViewById<RecyclerView>(R.id.rv_Discover_Movie)
        mainRV.layoutManager = LinearLayoutManager(context)
        mainRV.adapter = adapterMovie
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies()
        val searchView = v.findViewById<SearchView>(R.id.search_moviee)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                movieViewModel.setSearchMovie(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        movieViewModel.getMovie().observe(viewLifecycleOwner, Observer { items ->
            if (items != null) {
                adapterMovie.setData(items.results)
            } else {
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })
        return v

    }
}