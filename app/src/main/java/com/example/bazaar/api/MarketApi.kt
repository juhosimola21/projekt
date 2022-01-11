package com.example.bazaar.api

import com.example.bazaar.model.*
import com.example.bazaar.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String, @Header("limit") limit: Int = 300): ProductResponse

    @GET(Constants.GET_PROFILE_INFO_URL)
    suspend fun getProfileInfo(@Header("username") username: String): ProfileResponse

    @POST(Constants.REMOVE_PRODUCT_URL)
    suspend fun removeProduct(@Header("token") token: String,
                              @Query("product_id") product_id: String) :RemoveResponse
}