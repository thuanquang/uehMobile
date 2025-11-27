package com.example.buoi_8_1_retrofit.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("userid")
    val userId : Int,
    val id : Int,
    val title : String,
    val body : String
)


