package com.example.bazaar.model

import com.squareup.moshi.JsonClass

data class MyProduct(
    var title: String = "rossz",
    var description: String = "",
    var price_per_unit: String = "",
    var units: String = "",
    var is_active: Boolean = true,
    var rating: Double = 3.5,
    var amount_type: String = "",
    var price_type: String = ""
)

@JsonClass(generateAdapter = true)
data class Image(val _id: String, val image_id: String, val image_name: String, val image_path: String)

@JsonClass(generateAdapter = true)
data class Product(val rating: Double = 0.0,
                   val amount_type: String ="",
                   val price_type: String ="",
                   var product_id: String="",
                   val username: String="",
                   var is_active: Boolean =false,
                   var price_per_unit: String ="",
                   var units: String ="",
                   var description: String ="",
                   var title: String ="",
                   val creation_time: Long =0,
                   var order: String = "0"
)


@JsonClass(generateAdapter = true)
data class ProductResponse(val item_count: Int, val products: List<Product>, val timestamp: Long)

@JsonClass(generateAdapter = true)
data class ProfileResponse (var code: Int, var data : List<User>, val timestamp: Long)

@JsonClass(generateAdapter = true)
data class UserInfo (var username: String = "", var phone_number: String?, var email: String, var creation_time: Long)

@JsonClass(generateAdapter = true)
data class RemoveResponse(
    var message:String,
    var product_id:String,
    var deletion_time:Long
)

@JsonClass(generateAdapter = true)
data class AddProductRequest(
    var title: String,
    var description: String,
    var price_per_unit: String,
    var is_active: Boolean,
    var rating: Double,
    var amount_type: String,
    var units: String,
    var price_type: String
)

@JsonClass(generateAdapter = true)
data class AddProductResponse(
    var creation: String,
    var product_id: String,
    var username: String,
    var title: String,
    var description: String,
    var price_per_unit: String,
    var is_active: Boolean,
    var amount_type: String,
    var units: String,
    var rating: String,
    var creation_time: String,
    var price_type: String
)



