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