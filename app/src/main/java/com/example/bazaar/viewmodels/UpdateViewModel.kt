package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Product
import com.example.bazaar.model.UpdateRequest
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class UpdateViewModel (val repository: Repository) : ViewModel(){
    var product = MutableLiveData<Product>()

    init {
        product.value = Product()
    }

    fun updateProduct() {
        viewModelScope.launch {
            val request = UpdateRequest(
                title = product.value!!.title,
                price = product.value!!.price_per_unit,
                is_active = product.value!!.is_active,
                units = product.value!!.units,
                description = product.value!!.description
            )
            try {
                Log.d("xxx","${product.value!!.product_id}")
                val result = repository.updateProduct(MyApplication.token, product.value!!.product_id,request)
                Log.d("UpdateViewModel ok", "UpdateViewModel - #product:  $result")
            } catch (e: Exception) {

                Log.d("UpdateViewModel fail", "UpdateViewModel exception: ${e.toString()}")
            }
        }
    }
}