package com.azhar.uiupgradeproject.models

// Merepresentasikan data yang dikirim saat permintaan login
data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30 // Nilai default 30 menit
)