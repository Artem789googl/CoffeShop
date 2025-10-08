package com.aleriateam.buildcoffee.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aleriateam.buildcoffee.Activity.ItemsListActivity
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

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.titleCard.text = item.title
        updateAppearance(holder, position, context)
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(context, ItemsListActivity::class.java).apply {
                    putExtra("id", item.id.toString())
                    putExtra("title", item.title)
                }
                ContextCompat.startActivity(context, intent, null)
            }, 500)

        }


    }

    private fun updateAppearance(holder: Viewholder, position: Int, context: Context) {
        if (selectedPosition == position) {
            // Выбранный элемент
            holder.binding.titleCard.setBackgroundResource(R.drawable.brown_full_corner)
            holder.binding.titleCard.setTextColor(context.resources.getColor(R.color.white))
        } else {
            // Невыбранный элемент
            holder.binding.titleCard.setBackgroundResource(R.drawable.white_full_corner)
            holder.binding.titleCard.setTextColor(context.resources.getColor(R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int = items.size
}