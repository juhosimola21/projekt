package com.example.bazaar.model

import com.squareup.moshi.JsonClass

//import com.google.gson.annotations.SerializedName

data class User(var username: String="", var password: String="", var email: String="", var phone_number: String="")

@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class RegisterRequest (
    var username: String,
    var password: String,
    var email: String,
    var phone_number: String
)

@JsonClass(generateAdapter = true)
data class RegisterResponse (
    var code: Int,
    var message: String,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class SettingsRequest(
    var username: String?,
    var email: String?,
    var phone_number: String?
)

@JsonClass(generateAdapter = true)
data class UserUpdateResponse(
    var username: String?,
    var phone_number: String?,
    var email: String?,
    var token:String
)

@JsonClass(generateAdapter = true)
data class SettingsResponse(
    var code: Int,
    var updateData: UserUpdateResponse,
    var timestamp:Long
)

@JsonClass(generateAdapter = true)
data class UserResponse(
    var code:Int,
    var data: List<User>,
    val timestamp: Long
)

@JsonClass(generateAdapter = true)
data class UpdateRequest(
    var title: String?,
    var price: String?,
    var is_active: Boolean?,
    var units: String?,
    var description:String?
)

@JsonClass(generateAdapter = true)
data class UpdateProductData(
    var rating: Double,
    var amount_type: String,
    var price_type: String,
    var product_id: String,
    var username: String,
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    val creation_time: Long
)

@JsonClass(generateAdapter = true)
data class UpdateResponse(
    var updateData: UpdateProductData
)