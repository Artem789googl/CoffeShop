package com.aleriateam.buildcoffee.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aleriateam.buildcoffee.Domain.BannerModel
import com.aleriateam.buildcoffee.Domain.CategoryModel
import com.aleriateam.buildcoffee.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }
}