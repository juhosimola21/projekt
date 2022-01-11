package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.bazaar.MyApplication
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.model.Product
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository
import com.example.bazaar.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var currentPosition:Int = 0;
    var deleteId: MutableLiveData<String> = MutableLiveData()

    init{
        Log.d("xxx", "ListViewModel constructor - Token: ${MyApplication.token}")
        //getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result = repository.getProducts(MyApplication.token)
                products.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }

    fun getProduct() :Product{
        return products.value!![currentPosition]
    }

    fun removeProduct(){
        viewModelScope.launch {
            try {
                Log.d("xxx","${products.value!![currentPosition].product_id}")
                val result = repository.removeProduct(MyApplication.token, products.value!![currentPosition].product_id)
                deleteId.value = result.product_id
                Log.d("ListViewModel", "ListViewModel - #delete:  ${result.product_id}")
            }catch(e: Exception){
                Log.d("ListViewModel", "ListViewModel exception delete: ${e.toString()}")
            }
        }
    }
}