package com.cobacobaaja.themoviedb.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.data.Constant
import com.cobacobaaja.themoviedb.model.Results
import kotlinx.android.synthetic.main.item_container_movie.view.*
import kotlinx.android.synthetic.main.item_pager_movie.view.*
import kotlinx.android.synthetic.main.movietv_items.view.*

class MoviePagerAdapter:  RecyclerView.Adapter<MoviePagerAdapter.ViewHolder>(){
    private val mData = ArrayList<Results>()
    fun setData(items: ArrayList<Results>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(item : Results){
            with(itemView) {
                textMoviePager.text = item.title
                Glide.with(itemView.context)
                    .load(Constant.ImgUrl +item.posterPath)
                    .apply(RequestOptions().override(500, 500))
                    .into(imageViewMoviePager)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_container_movie, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount() : Int = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }
}