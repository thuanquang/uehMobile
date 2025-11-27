package com.example.buoi_8_1_retrofit.model

import com.example.buoi_8_1_retrofit.CmmVariable.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiJsonPlaceholder: APIJSONPlaceholder by lazy {
        retrofit.create(APIJSONPlaceholder::class.java)
    }
}


