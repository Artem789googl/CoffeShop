package com.aleriateam.buildcoffee.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleriateam.buildcoffee.Adapter.CartAdapter
import com.aleriateam.buildcoffee.Helper.ChangeNumberItemsListener
import com.aleriateam.buildcoffee.Helper.ManagmentCart
import com.aleriateam.buildcoffee.R
import com.aleriateam.buildcoffee.databinding.ActivityCartBinding
import kotlinx.coroutines.MainScope
import kotlin.random.Random

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        binding.apply {
            listView.layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            listView.adapter = CartAdapter(
                managmentCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculateCart()
                    }
                }
            )
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = Random.nextInt(5, 50)
        tax = (managmentCart.getTotalFee()* percentTax * 100) / 100.0
        val totalPrice =( (managmentCart.getTotalFee() + tax + delivery) * 100 ) / 100.0
        val itemtotal = (managmentCart.getTotalFee()*100 ) / 100.0
        binding.apply {
            totalFreeText.text = "\$$itemtotal"
            totalTaxText.text = "\$$tax"
            deliveryText.text = "\$$delivery"
            totalText.text = "\$$totalPrice"
        }
    }
}