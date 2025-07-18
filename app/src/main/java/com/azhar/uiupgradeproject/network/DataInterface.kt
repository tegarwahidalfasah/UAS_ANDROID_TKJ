package com.azhar.uiupgradeproject.network

import com.azhar.uiupgradeproject.model.ProductResponse
import com.azhar.uiupgradeproject.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface DataInterface {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("products")
    fun getProducts(
        @Header("Authorization") token: String
    ): Call<ProductResponse>

    @GET("products/search")
    fun getSearchProduct(
        @Query("q") phone: String,
        @Header("Authorization") token: String
    ): Call<ProductResponse>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "posts", hasBody = true)
    fun deleteProduct(
        @Field("id") id: String?,
        @Header("Authorization") token: String
    ): Call<ProductResponse>
}
