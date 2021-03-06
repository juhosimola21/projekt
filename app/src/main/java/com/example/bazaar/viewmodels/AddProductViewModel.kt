package com.example.bazaar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.AddProductRequest
import com.example.bazaar.model.MyProduct
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.utils.Constants
import com.example.bazaar.utils.Constants.ID_PRODUCT
import kotlinx.coroutines.launch

class AddProductViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var product = MutableLiveData<MyProduct>()
    var products: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        product.value = MyProduct()
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

    suspend fun addProduct() {
        val request =
            AddProductRequest(title = product.value!!.title,
                description = product.value!!.description,
                price_per_unit = product.value!!.price_per_unit,
                units = product.value!!.units,
                is_active = product.value!!.is_active,
                rating = product.value!!.rating,
                amount_type = product.value!!.amount_type,
                price_type = product.value!!.price_type)

        try {
            val result = repository.addProduct(MyApplication.token, request)
            Log.d("xxx", "${result.creation}")
            ID_PRODUCT = result.product_id
            Log.d("xxx", "A TERMEK ID: $ID_PRODUCT")

        } catch (e: Exception) {
            Log.d("xxx", "DetailScretchViewModel - exception: ${e.toString()}")
            Constants.ERROR = 2
        }
    }
}