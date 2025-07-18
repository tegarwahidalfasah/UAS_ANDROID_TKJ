package com.azhar.uiupgradeproject.data.model // Sesuaikan dengan package ProductModel Anda

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double, // Perhatikan: harga biasanya Double/Int dari API
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String, // URL gambar
    val images: List<String>
)