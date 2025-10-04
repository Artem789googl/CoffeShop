package com.aleriateam.buildcoffee.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleriateam.buildcoffee.Activity.DetailActivity
import com.aleriateam.buildcoffee.Domain.ItemsModel
import com.aleriateam.buildcoffee.databinding.ViewholderPopularBinding
import com.bumptech.glide.Glide

class PopularAdapter(val items: MutableList<ItemsModel>):
RecyclerView.Adapter<PopularAdapter.Viewholder>(){

    lateinit var context: Context
    inner class Viewholder(val binding: ViewholderPopularBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        holder.binding.titleText.text = items[position].title
        holder.binding.priceText.text = "\$ ${items[position].price}"
        holder.binding.subtitleText.text = items[position].extra

        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}