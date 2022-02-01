package com.example.bazaar.repository

import android.util.Log
import com.example.bazaar.api.RetrofitInstance
import com.example.bazaar.model.*

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun getProfileInfo(username: String): ProfileResponse {
        return RetrofitInstance.api.getProfileInfo(username)
    }

    suspend fun removeProduct(token:String, product_id:String): RemoveResponse {
        return RetrofitInstance.api.removeProduct(token,product_id)
    }

    suspend fun addProduct(token: String, request: AddProductRequest): AddProductResponse {
        return RetrofitInstance.api.addProduct(token, request.title, request.description, request.price_per_unit,
            request.units, request.is_active, request.rating, request.amount_type, request.price_type)
    }

    suspend fun updateUser(token: String, request: SettingsRequest): SettingsResponse {
        return RetrofitInstance.api.updateUser(token, request)
    }

    suspend fun updateProduct(token :String, product_id: String, request:UpdateRequest) : UpdateResponse {
        return RetrofitInstance.api.updateProduct(token,product_id, request)
    }
}
