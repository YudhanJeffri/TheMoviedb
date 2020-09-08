package com.cobacobaaja.themoviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobacobaaja.themoviedb.model.Results

class MovieVM : ViewModel() {
    val listMovie = MutableLiveData<ArrayList<Results>>()

}