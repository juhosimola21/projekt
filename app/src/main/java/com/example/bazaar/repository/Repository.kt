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
}