package com.example.buoi_8_1_retrofit.model

import retrofit2.Response
import retrofit2.http.GET

interface APIJSONPlaceholder {
    @GET("/posts/1")
    suspend fun getPostItem(): Response<Post>

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}


