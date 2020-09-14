package com.cobacobaaja.themoviedb.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.adapter.MoviePagerAdapter
import com.cobacobaaja.themoviedb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_now_showing.*


class NowShowingFragment : Fragment() {
    private lateinit var moviePagerAdapter: MoviePagerAdapter
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_showing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        loadPagerMovie()
    }

    fun loadPagerMovie(){
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.loadPagerMovie()
        movieViewModel.getMovie().observe(viewLifecycleOwner, Observer { items ->
            if (items != null) {
                moviePagerAdapter.setData(items.results)
            } else {
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })
    }

    @SuppressLint("WrongConstant")
    private fun initRv() {
        moviePagerAdapter = MoviePagerAdapter()
        moviePagerAdapter.notifyDataSetChanged()
        rvPagerMovie.layoutManager = LinearLayoutManager(this.context, OrientationHelper.HORIZONTAL, false)
        rvPagerMovie.adapter = moviePagerAdapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvPagerMovie)
    }
}