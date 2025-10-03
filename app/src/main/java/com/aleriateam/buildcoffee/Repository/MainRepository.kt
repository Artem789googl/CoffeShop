package com.aleriateam.buildcoffee.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aleriateam.buildcoffee.Domain.BannerModel
import com.aleriateam.buildcoffee.Domain.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    //Initiation FireBases's database
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    /***
     * Load banners from database
     */
    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        val listData = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BannerModel::class.java)
                    item?.let { list.add(item) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("BannerModel", "Error")
            }

        })
        return listData
    }

    /***
     * Load categories from database
     */
    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(item) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("CategoryModel", "Error: ${error.message}")
            }

        })
        return listData
    }
}