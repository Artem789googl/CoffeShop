package com.aleriateam.buildcoffee.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleriateam.buildcoffee.Domain.ItemsModel
import com.aleriateam.buildcoffee.Helper.ChangeNumberItemsListener
import com.aleriateam.buildcoffee.Helper.ManagmentCart
import com.aleriateam.buildcoffee.databinding.ViewholderCartBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemListener: ChangeNumberItemsListener?=null
) : RecyclerView.Adapter<CartAdapter.ViewHolder> () {

    class ViewHolder (val binding: ViewholderCartBinding) :
    RecyclerView.ViewHolder(binding.root)

    private val managmentCart: ManagmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleText.text = item.title
        holder.binding.feeEachItem.text = "\$${item.price}"
        holder.binding.totalEachItem.text = "\$${item.numberInCart*item.price}"
        holder.binding.numberInCartText.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transforms(CenterCrop()))
            .into(holder.binding.pipCart)

        holder.binding.plusBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }

        holder.binding.minusBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }

        holder.binding.removeItemBtn.setOnClickListener {
            managmentCart.removeItem(listItemSelected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }

    }

    override fun getItemCount(): Int = listItemSelected.size
}