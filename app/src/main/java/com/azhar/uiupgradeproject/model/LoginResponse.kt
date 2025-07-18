package com.azhar.uiupgradeproject.models

// Merepresentasikan respons dari API login yang sukses
data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String // Token autentikasi yang didapat setelah login
)