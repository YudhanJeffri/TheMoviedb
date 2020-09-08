package com.cobacobaaja.themoviedb.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.data.Constant.ImgUrl
import com.cobacobaaja.themoviedb.model.Results
import kotlinx.android.synthetic.main.movietv_items.view.*

class MovieAdapter(private val mData : ArrayList<Results>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    fun setData(items: ArrayList<Results>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(item : Results){
            with(itemView) {
                textmovietv.text = item.title
                Glide.with(itemView.getContext())
                    .load(ImgUrl+item.posterPath)
                    .apply(RequestOptions().override(500, 500))
                    .into(imageView)
                release_date.text = "Release Date : " + item.release_date
                popularity.text = "Popularity : " + item.popularity
                vote_average.text = "Vote Average : "+ item.vote_average
                vote_count.text = "Vote Count : "+item.vote_count
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.movietv_items, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount() : Int = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }
}