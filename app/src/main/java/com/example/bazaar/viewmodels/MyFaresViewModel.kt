package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class MyFaresViewModel (private val repository: Repository) : ViewModel(){
    var orders: MutableLiveData<List<Product>> = MutableLiveData()
    var currentPosition: Int = 0

    init{
        Log.d("xxx", "MyFares constructor - Token: ${MyApplication.token}")
        getOrders()
    }

    fun getOrders() {
        viewModelScope.launch {
            try {
                val result = repository.getProducts(MyApplication.token)
                orders.value = result.products
                Log.d("xxx", "Atadta a megrendeleseket")

            }catch(e: Exception){
                Log.d("xxx", "MyFares exception: ${e.toString()}")
            }
        }
    }
}