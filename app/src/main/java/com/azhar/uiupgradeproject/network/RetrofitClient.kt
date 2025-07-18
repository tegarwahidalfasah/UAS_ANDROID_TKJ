// app/src/main/java/com/azhar/uiupgradeproject/network/RetrofitClient.kt
package com.azhar.uiupgradeproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Pastikan ini adalah ApiService
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}