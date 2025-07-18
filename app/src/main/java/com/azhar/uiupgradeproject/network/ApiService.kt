package com.azhar.uiupgradeproject.network

import com.azhar.uiupgradeproject.data.model.ProductModel
import com.azhar.uiupgradeproject.model.ProductResponse
import com.azhar.uiupgradeproject.data.model.RecipeResponse
import com.azhar.uiupgradeproject.models.LoginRequest
import com.azhar.uiupgradeproject.models.LoginResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(@Header("Authorization") token: String): Response<ProductResponse>

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Header("Authorization") token: String
    ): Response<ProductResponse>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<ProductModel>

    @GET("recipes")
    suspend fun getAllRecipes(): Response<RecipeResponse>
}
