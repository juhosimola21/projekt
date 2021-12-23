package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class MyMarketViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var myproducts: ArrayList<Product> = ArrayList()

    init{
        Log.d("xxx", "ListViewModel constructor - Token: ${MyApplication.token}")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val myname = MyApplication.username
                val result = repository.getProducts(MyApplication.token)
                for(it in result.products){
                    var iterator = it.username
                    Log.d("it","$iterator")
                    Log.d("my name", "$myname")
                    if(iterator == myname){
                        myproducts!!.add(it)
                    }
                }
                products.value = myproducts
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }
}