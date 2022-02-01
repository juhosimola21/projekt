package com.example.bazaar.api

import com.example.bazaar.model.*
import com.example.bazaar.utils.Constants
import com.example.bazaar.utils.Constants.ID_PRODUCT
import com.example.bazaar.utils.Constants.UPDATE_DATA_URL
import com.example.bazaar.utils.Constants.UPDATE_PRODUCT_URL
import com.squareup.moshi.JsonClass
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

    @Multipart
    @JsonClass(generateAdapter = true)
    @POST(Constants.ADD_PRODUCT_URL)
    suspend fun addProduct(
        @Header("token") token: String,
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("price_per_unit") price_per_unit: String,
        @Part("units") units: String,
        @Part("is_active") is_active: Boolean,
        @Part("rating") rating: Double,
        @Part("amount_type") amount_type: String,
        @Part("price_type") price_type: String
    ): AddProductResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProductLast(
        @Header("token") token: String,
        @Header("filter") filter: String = "{\"product_id\": \"$ID_PRODUCT\"}"
    ): AddProductResponse

    @POST(UPDATE_DATA_URL)
    suspend fun updateUser(
        @Header("token") token: String,
        @Body request: SettingsRequest
    ): SettingsResponse

    @POST(UPDATE_PRODUCT_URL)
    suspend fun updateProduct(
        @Header("token") token: String,
        @Query("product_id") product_id: String,
        @Body request: UpdateRequest
    ): UpdateResponse
}