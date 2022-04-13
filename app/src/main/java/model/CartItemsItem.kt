package model

import com.google.gson.annotations.SerializedName

data class CartItems(
    @SerializedName("__v")
    val v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)