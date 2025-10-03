package com.aleriateam.buildcoffee.Adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleriateam.buildcoffee.Domain.CategoryModel
import com.aleriateam.buildcoffee.R
import com.aleriateam.buildcoffee.databinding.ViewholdercategoryBinding

class CategoryAdapter(val items: MutableList<CategoryModel>):
RecyclerView.Adapter<CategoryAdapter.Viewholder>(){

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    inner class Viewholder(val binding: ViewholdercategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.Viewholder {
        context = parent.context
        val binding = ViewholdercategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleCard.text = item.title

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({

            }, 500)
            if (selectedPosition == position) {
                holder.binding.titleCard.setBackgroundColor(R.drawable.brown_full_corner)
                holder.binding.titleCard.setTextColor(context.resources.getColor(R.color.white))
            } else {
                holder.binding.titleCard.setBackgroundColor(R.drawable.white_full_corner)
                holder.binding.titleCard.setTextColor(context.resources.getColor(R.color.darkBrown))
            }
        }
    }

    override fun getItemCount(): Int = items.size
}