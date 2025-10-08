package com.aleriateam.buildcoffee.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleriateam.buildcoffee.Adapter.CategoryAdapter
import com.aleriateam.buildcoffee.Adapter.PopularAdapter
import com.aleriateam.buildcoffee.ViewModel.MainViewModel
import com.aleriateam.buildcoffee.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            val intent = Intent(this, CartActivity()::class.java)
            startActivity(intent)
        }
    }

    private fun initPopular() {
        binding.progressBarBaner.visibility = View.VISIBLE
        viewModel.loadPopular().observeForever {
            binding.recycleViewPopular.layoutManager = GridLayoutManager(this, 2)
            binding.recycleViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        }
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.categoryView.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )
            binding.categoryView.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initBanner() {
        binding.progressBarBaner.visibility = View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)
            binding.progressBarBaner.visibility = View.GONE
        }
        viewModel.loadBanner()
    }
}