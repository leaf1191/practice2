package com.kotlin.practice2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.practice2.R
import com.kotlin.practice2.db.entity.InterestCoinEntity

class CoinListRVAdapter(val context : Context, val data : List<InterestCoinEntity>) : RecyclerView.Adapter<CoinListRVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position: Int)
    }

    var itemClick : ItemClick? = null

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val coinName = view.findViewById<TextView>(R.id.coinName)
        val likeBtn = view.findViewById<ImageView>(R.id.likeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_coin_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.likeBtn.setOnClickListener{ v ->
            itemClick?.onClick(v, position)
        }

        holder.coinName.text = data[position].coin_name
        val selected = data[position].selected
        if(selected){
            holder.likeBtn.setImageResource(R.drawable.like_red)
        } else {
            holder.likeBtn.setImageResource(R.drawable.like_grey)
        }
    }

}