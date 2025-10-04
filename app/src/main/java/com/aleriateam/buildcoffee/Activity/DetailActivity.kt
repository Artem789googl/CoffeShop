package com.aleriateam.buildcoffee.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aleriateam.buildcoffee.Domain.ItemsModel
import com.aleriateam.buildcoffee.Helper.ManagmentCart
import com.aleriateam.buildcoffee.R
import com.aleriateam.buildcoffee.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import java.math.BigInteger

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        bundle()
        initSizeList()

    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.brown_stroke_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.brown_stroke_bg)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.brown_stroke_bg)
            }

        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel
            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleBtn.text = item.title
            descriptionText.text = item.description
            priceText.text = "\$${item.price}"
            ratingText.text = item.rating.toString()
            addToCartBtn.setOnClickListener {
                item.numberInCart = numberInCartText.text.toString().toInt()
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener {
                finish()
            }
            plusBtn.setOnClickListener {
                numberInCartText.text = (item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusBtn.setOnClickListener {
                if (item.numberInCart > 0) {
                    numberInCartText.text = (item.numberInCart-1).toString()
                    item.numberInCart--
                }

            }
        }
    }
}