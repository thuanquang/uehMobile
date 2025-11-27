package com.example.buoi_8_1_retrofit.repository

import com.example.buoi_8_1_retrofit.model.Post
import com.example.buoi_8_1_retrofit.model.RetrofitInstance
import retrofit2.Response

class APIPostRepository {
    suspend fun getPostItem(): Response<Post> {
        return RetrofitInstance.apiJsonPlaceholder.getPostItem()
    }

    suspend fun getPosts(): Response<List<Post>> {
        return RetrofitInstance.apiJsonPlaceholder.getPosts()
    }
}


